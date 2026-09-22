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

   public Burger() {
   }

   public Burger(String name, Restaurant restaurant, Double rating, String notes) {
      this.name = name;
      this.restaurant = restaurant;
      this.rating = rating;
      this.notes = notes;
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
}