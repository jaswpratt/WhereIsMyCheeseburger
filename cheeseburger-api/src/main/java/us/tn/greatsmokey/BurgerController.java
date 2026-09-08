/**
 * 
 */
package us.tn.greatsmokey;

import org.springframework.web.bind.annotation.*;
import java.util.List;
 
/**
 * 
 */
@RestController
@RequestMapping("/api/burgers")
@CrossOrigin(origins = "*") // tighten this to your real frontend origin before going live
public class BurgerController {

   private final BurgerRepository repository;

   public BurgerController(BurgerRepository repository) {
      this.repository = repository;
   }

   @GetMapping
   public List<Burger> getAllBurgers() {
      return repository.findAll();
   }

   @PostMapping
   public Burger createBurger(@RequestBody Burger burger) {
      return repository.save(burger);
   }
}
