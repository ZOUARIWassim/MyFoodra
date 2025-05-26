package fr.cs.groupJ.myFoodora.model.fidelityCard;

import fr.cs.groupJ.myFoodora.model.restaurant.Restaurant;
import fr.cs.groupJ.myFoodora.model.user.Customer;

public abstract class FidelityCard {

    private Restaurant restaurant;
    private Customer customer;

    public FidelityCard(Restaurant restaurant, Customer customer) {
        this.restaurant = restaurant;
        this.customer = customer;
    }

    // === Getters and Setters ===
    public Restaurant getRestaurant() {
        return restaurant;
    }
    protected void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    public Customer getCustomer() {
        return customer;
    }
    protected void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // === Abstract Method ===
    public abstract double computeFinalPrice(double basePrice);
}
