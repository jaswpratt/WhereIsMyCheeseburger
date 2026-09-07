/**
 * 
 */
package us.tn.greatsmokey;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


/**
 * 
 */
@Entity
public class Burger {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;
    
   private String restaurantName;
    
   private Double rating;
    
   private String notes;

   public Burger() { }

   /**
    * @return the id
    */
   public Long getId() {
      return id;
   }

   /**
    * @param id the id to set
    */
   public void setId(Long id) {
      this.id = id;
   }

   /**
    * @return the name
    */
   public String getName() {
      return name;
   }

   /**
    * @param name the name to set
    */
   public void setName(String name) {
      this.name = name;
   }

   /**
    * @return the restaurantName
    */
   public String getRestaurantName() {
      return restaurantName;
   }

   /**
    * @param restaurantName the restaurantName to set
    */
   public void setRestaurantName(String restaurantName) {
      this.restaurantName = restaurantName;
   }

   /**
    * @return the rating
    */
   public Double getRating() {
      return rating;
   }

   /**
    * @param rating the rating to set
    */
   public void setRating(Double rating) {
      this.rating = rating;
   }

   /**
    * @return the notes
    */
   public String getNotes() {
      return notes;
   }

   /**
    * @param notes the notes to set
    */
   public void setNotes(String notes) {
      this.notes = notes;
   }

   @Override
   public String toString() {
      return "Burger [id=" + id + ", name=" + name + ", restaurantName=" + restaurantName + ", rating=" + rating
            + ", notes=" + notes + "]";
   }

}