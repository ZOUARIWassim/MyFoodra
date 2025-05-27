package fr.cs.groupJ.myFoodora.model.order;

import fr.cs.groupJ.myFoodora.model.fidelityCard.FidelityCard;
import fr.cs.groupJ.myFoodora.model.restaurant.Restaurant;
import fr.cs.groupJ.myFoodora.model.user.Customer;
import fr.cs.groupJ.myFoodora.model.item.Item;
import fr.cs.groupJ.myFoodora.util.Date;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private String orderName;
    private Customer customer;
    private Restaurant restaurant;

    private Date orderDate = null;
    private boolean isFinalized = false;
    private List<Item> items = new ArrayList<>();

    public Order( String name, Customer customer,Restaurant restaurant) {
        this.orderName = name;
        this.customer = customer;
        this.restaurant = restaurant;
    }
    // ===== Getters and Setters =====

    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void customer(Customer customer) {
        this.customer = customer;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
    public String getOrderName() {
        return orderName;
    }
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
    public Restaurant getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    public boolean isFinalized() {
        return isFinalized;
    }

    // ===== Methods =====
    public void finalizeOrder(Date inputDate) {
        if (items.isEmpty()) {
            throw new IllegalStateException("Cannot finalize order with no items.");
        }
        if (isFinalized) {
            throw new IllegalStateException("Order is already finalized.");
        }
        this.orderDate = inputDate;
        this.isFinalized = true;
    }
    public void addItem(Item item) {
        if (items != null) {
            items.add(item);
        } else {
            throw new IllegalStateException("Items list is not initialized.");
        }
    }
    public void removeItem(Item item) {
        if (items != null && items.contains(item)) {
            items.remove(item);
        } else {
            throw new IllegalStateException("Item not found in the order.");
        }
    }
    public double calculateBasePrice() {
        double total = 0.0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }
    public double calculateFinalPrice() {
        FidelityCard fidelityCard = customer.getFidelityCard(restaurant);
        double basePrice = calculateBasePrice();
        if (fidelityCard != null) {
            return fidelityCard.computeFinalPrice(basePrice);
        } else {
            throw new IllegalStateException("Customer does not have a fidelity card.");
        }
    }
}