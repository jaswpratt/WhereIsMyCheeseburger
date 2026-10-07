/**
 * 
 */
package us.tn.greatsmokey;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

   /**
    * Spring Data JPA repository for {@link Restaurant} entities.
    * <p>
    * Provides standard CRUD operations via Spring Data's generated
    * implementation, plus a lookup by name used to find or create a
    * restaurant when a new burger is logged against it.
    */
   public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
   
      /**
       * Looks up a restaurant by its exact name.
       * <p>
       * Used by {@code BurgerController} when logging a new burger, to reuse
       * an existing restaurant record rather than creating a duplicate.
       *
       * @param name the restaurant name to search for
       * @return an {@link Optional} containing the matching restaurant, or
       *         empty if no restaurant with that name exists
       */
      Optional<Restaurant> findByName(String name);
   }
