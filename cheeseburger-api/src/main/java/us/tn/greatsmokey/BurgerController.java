/**
 * 
 */
package us.tn.greatsmokey;

import org.springframework.web.bind.annotation.*;
import us.tn.greatsmokey.BurgerRepository;
import java.util.List;
import java.util.stream.Collectors;
 
/**
 * 
 */
@RestController
@RequestMapping("/api/burgers")
public class BurgerController {
   private final BurgerRepository burgerRepository;
   private final RestaurantRepository restaurantRepository;

   public BurgerController(BurgerRepository burgerRepository, RestaurantRepository restaurantRepository) {
      this.burgerRepository = burgerRepository;
      this.restaurantRepository = restaurantRepository;
   }

   @GetMapping
   public List<BurgerResponse> getAllBurgers() {
      return burgerRepository.findAll().stream()
            .map(BurgerResponse::from)
            .collect(Collectors.toList());
   }

   @PostMapping
   public BurgerResponse createBurger(@RequestBody BurgerRequest request) {
       Restaurant restaurant = restaurantRepository.findByName(request.restaurantName()).orElseGet(() -> {
                   Restaurant newRestaurant = new Restaurant(request.restaurantName());
                   if (request.restaurantState() != null && !request.restaurantState().isBlank()) {
                       newRestaurant.setState(request.restaurantState());
                   }
                   return restaurantRepository.save(newRestaurant);
               });

       Burger burger = new Burger(request.name(), restaurant, request.rating(), request.notes(), request.side(), request.drink());
       Burger saved = burgerRepository.save(burger);
       return BurgerResponse.from(saved);
   }

   // Request shape: still flat, matches what the React form already sends
   public record BurgerRequest(String name, String restaurantName, String restaurantState, Double rating, String notes, String side, String drink)  { }

   // Response shape: flattens restaurant back to a plain name, so React doesn't need to change
   public record BurgerResponse(Long id, String name, String restaurantName, String restaurantState, Double rating, String notes, String side, String drink) {
      static BurgerResponse from(Burger burger) {
         Restaurant r = burger.getRestaurant();
         return new BurgerResponse(
        		 burger.getId(), 
        		 burger.getName(),
        		 r != null ? r.getName() : null,
                 r != null ? r.getState() : null,
                 burger.getRating(), 
                 burger.getNotes(), 
                 burger.getSide(),
                 burger.getDrink());
      }
   }
}
