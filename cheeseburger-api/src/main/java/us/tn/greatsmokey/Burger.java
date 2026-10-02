package us.tn.greatsmokey;

import jakarta.persistence.*;

@Entity
public class Burger {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

   @ManyToOne
   @JoinColumn(name = "restaurant_id")
   private Restaurant restaurant;

   private Double rating;
   private String notes;
   private String side;
   private String drink;
   
   public Burger() { }

   public Burger(String name, Restaurant restaurant, Double rating, String notes, String side, String drink) {
      this.name = name;
      this.restaurant = restaurant;
      this.rating = rating;
      this.notes = notes;
      this.side = side;
      this.drink = drink;
   }

   public Long getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Restaurant getRestaurant() {
      return restaurant;
   }

   public void setRestaurant(Restaurant restaurant) {
      this.restaurant = restaurant;
   }

   public Double getRating() {
      return rating;
   }

   public void setRating(Double rating) {
      this.rating = rating;
   }

   public String getNotes() {
      return notes;
   }

   public void setNotes(String notes) {
      this.notes = notes;
   }

   /**
    * @return the side
    */
   public String getSide() {
      return side;
   }

   /**
    * @param side the side to set
    */
   public void setSide(String side) {
      this.side = side;
   }

   /**
    * @return the drink
    */
   public String getDrink() {
      return drink;
   }

   /**
    * @param drink the drink to set
    */
   public void setDrink(String drink) {
      this.drink = drink;
   }

   @Override
   public String toString() {
   return "Burger [id=" + id + ", name=" + name + ", restaurant=" + restaurant + ", rating=" + rating + ", notes="
         + notes + ", side=" + side + ", drink=" + drink + "]";
   }
}