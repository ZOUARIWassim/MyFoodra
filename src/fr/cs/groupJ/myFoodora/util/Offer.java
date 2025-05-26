package fr.cs.groupJ.myFoodora.util;

import fr.cs.groupJ.myFoodora.model.restaurant.Restaurant;

public class Offer {
    private Restaurant restaurant;
    private String description;

    public Offer(Restaurant restaurant, String description) {
        this.restaurant = restaurant;
        this.description = description;
    }

    // ====== Getters and Setters =====
    public Restaurant getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "restaurant=" + restaurant.getName() +
                ", description='" + description + '\'' +
                '}';
    }
}