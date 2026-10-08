/**
 * 
 */
package us.tn.greatsmokey;

import org.springframework.web.bind.annotation.*;
import us.tn.greatsmokey.BurgerRepository;
import java.util.List;
import java.util.stream.Collectors;
 
/**
 * REST controller exposing CRUD-style endpoints for {@link Burger} entries.
 * <p>
 * Handles listing all logged burgers and creating new ones. When a burger is
 * created, the associated {@link Restaurant} is looked up by name and
 * reused if it already exists, or created on the fly if this is the first
 * burger logged there. Request and response bodies are kept as flat records
 * (restaurant fields inlined) so the React frontend doesn't need to know
 * about the underlying entity relationships.
 */
@RestController
@RequestMapping("/api/burgers")
public class BurgerController {
   private final BurgerRepository burgerRepository;
   private final RestaurantRepository restaurantRepository;

   /**
    * Creates a new {@code BurgerController}.
    *
    * @param burgerRepository repository for reading and writing {@link Burger} entities
    * @param restaurantRepository repository for reading and writing {@link Restaurant} entities
    */
   public BurgerController(BurgerRepository burgerRepository, RestaurantRepository restaurantRepository) {
      this.burgerRepository = burgerRepository;
      this.restaurantRepository = restaurantRepository;
   }

   /**
    * Returns every burger currently logged, flattened into {@link BurgerResponse}
    * objects for the frontend.
    *
    * @return the list of all logged burgers
    */
   @GetMapping
   public List<BurgerResponse> getAllBurgers() {
      return burgerRepository.findAll().stream()
            .map(BurgerResponse::from)
            .collect(Collectors.toList());
   }

   /**
    * Logs a new burger.
    * <p>
    * The restaurant named in the request is looked up by name; if no
    * matching restaurant exists yet, one is created (and tagged with a
    * state, if provided) before the burger is saved against it.
    *
    * @param request the burger details submitted by the client
    * @return the newly saved burger, flattened into a {@link BurgerResponse}
    */
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

   /**
    * Incoming shape for creating a burger. Still flat, matching what the
    * React form already sends — the restaurant is identified by name
    * (and optional state) rather than by id.
    *
    * @param name the name of the burger
    * @param restaurantName the name of the restaurant where it was eaten
    * @param restaurantState the state the restaurant is in, used only when creating a new restaurant
    * @param rating the user's rating for the burger
    * @param notes freeform notes about the burger
    * @param side the side item that accompanied the burger
    * @param drink the drink that accompanied the burger
    */
   // Request shape: still flat, matches what the React form already sends
   public record BurgerRequest(String name, String restaurantName, String restaurantState, Double rating, String notes, String side, String drink)  { }

   /**
    * Outgoing shape for a burger. Flattens the associated {@link Restaurant}
    * back down to its name and state, so the React frontend doesn't need to
    * change to handle a nested object.
    *
    * @param id the burger's database id
    * @param name the name of the burger
    * @param restaurantName the name of the restaurant where it was eaten, or {@code null} if unset
    * @param restaurantState the state the restaurant is in, or {@code null} if unset
    * @param rating the user's rating for the burger
    * @param notes freeform notes about the burger
    * @param side the side item that accompanied the burger
    * @param drink the drink that accompanied the burger
    */
   // Response shape: flattens restaurant back to a plain name, so React doesn't need to change
   public record BurgerResponse(Long id, String name, String restaurantName, String restaurantState, Double rating, String notes, String side, String drink) {
      
      /**
       * Builds a {@code BurgerResponse} from a {@link Burger} entity, flattening
       * its associated restaurant (if any) into plain name/state fields.
       *
       * @param burger the burger entity to convert
       * @return the flattened response representation
       */
      static BurgerResponse from(Burger burger) {
         Restaurant restaurant = burger.getRestaurant();
         return new BurgerResponse(
               burger.getId(), 
               burger.getName(),
               restaurant != null ? restaurant.getName() : null,
               restaurant != null ? restaurant.getState() : null,
                 burger.getRating(), 
                 burger.getNotes(), 
                 burger.getSide(),
                 burger.getDrink());
      }
   }
}
