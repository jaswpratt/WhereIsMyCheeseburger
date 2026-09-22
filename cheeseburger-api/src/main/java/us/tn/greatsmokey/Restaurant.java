/**
 * 
 */
package us.tn.greatsmokey;

import jakarta.persistence.*;

/**
 * 
 */
@Entity
@Table(name = "restaurant")
public class Restaurant {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "restaurant_id")
   private Integer restaurantId;

   private String name;
   private String location;
   private String type;
   private String specialty;

   public Restaurant() { }
   
   public Restaurant(String restaurantName) { 
	   this.name = restaurantName;
   }

   /**
    * @return the restaurantId
    */
   public Integer getRestaurantId() {
      return restaurantId;
   }

   /**
    * @param restaurantId the restaurantId to set
    */
   public void setRestaurantId(Integer restaurantId) {
      this.restaurantId = restaurantId;
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
    * @return the location
    */
   public String getLocation() {
      return location;
   }

   /**
    * @param location the location to set
    */
   public void setLocation(String location) {
      this.location = location;
   }

   /**
    * @return the type
    */
   public String getType() {
      return type;
   }

   /**
    * @param type the type to set
    */
   public void setType(String type) {
      this.type = type;
   }

   /**
    * @return the specialty
    */
   public String getSpecialty() {
      return specialty;
   }

   /**
    * @param specialty the specialty to set
    */
   public void setSpecialty(String specialty) {
      this.specialty = specialty;
   }

   @Override
   public String toString() {
      return "Restaurant [restaurantId=" + restaurantId + ", name=" + name + ", location=" + location + ", type="
             + type + ", specialty=" + specialty + "]";
   }
}
