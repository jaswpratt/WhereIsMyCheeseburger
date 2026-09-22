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
      Restaurant restaurant = restaurantRepository.findByName(request.restaurantName())
            .orElseGet(() -> restaurantRepository.save(new Restaurant(request.restaurantName())));

      Burger burger = new Burger(request.name(), restaurant, request.rating(), request.notes());
      Burger saved = burgerRepository.save(burger);
      return BurgerResponse.from(saved);
   }

   // Request shape: still flat, matches what the React form already sends
   public record BurgerRequest(String name, String restaurantName, Double rating, String notes) { }

   // Response shape: flattens restaurant back to a plain name, so React doesn't need to change
   public record BurgerResponse(Long id, String name, String restaurantName, Double rating, String notes) {
      static BurgerResponse from(Burger burger) {
         return new BurgerResponse(
               burger.getId(),
               burger.getName(),
               burger.getRestaurant() != null ? burger.getRestaurant().getName() : null,
               burger.getRating(),
               burger.getNotes()
         );
      }
   }
}
