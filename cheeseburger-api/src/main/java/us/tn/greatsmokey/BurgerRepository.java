/**
 * 
 */
package us.tn.greatsmokey;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for {@link Burger} entities.
 * <p>
 * Provides standard CRUD and query operations (save, findAll, findById,
 * delete, etc.) for burgers via Spring Data's generated implementation —
 * no method bodies are needed here. The id type is {@link Long}, matching
 * {@link Burger}'s auto-generated primary key.
 */
public interface BurgerRepository extends JpaRepository<Burger, Long> {

}