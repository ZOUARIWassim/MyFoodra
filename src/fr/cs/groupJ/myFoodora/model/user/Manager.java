
package com.myfoodora.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a system manager in the myFoodora system.
 */
public class Manager {
    private final String id;
    private String firstName;
    private String lastName;
    private String username;

    /**
     * Constructs a new Manager.
     */
    public Manager(String id, String firstName, String lastName, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    /**
     * Adds a new user (Restaurant, Customer, or Courier) to the system.
     */
    public void addUser(User user) {
        UserRepository.getInstance().add(user);
    }

    /**
     * Removes a user by ID.
     */
    public void removeUser(String userId) {
        UserRepository.getInstance().remove(userId);
    }

    /**
     * Activates a user account.
     */
    public void activateUser(String userId) {
        UserRepository.getInstance().setActive(userId, true);
    }

    /**
     * Deactivates a user account.
     */
    public void deactivateUser(String userId) {
        UserRepository.getInstance().setActive(userId, false);
    }

    /**
     * Updates service fee, markup, or delivery cost.
     */
    public void updatePricingPolicy(double serviceFeePct, double markupPct, double deliveryCost) {
        PricingPolicy policy = PricingPolicy.getInstance();
        policy.setServiceFeePercentage(serviceFeePct);
        policy.setMarkupPercentage(markupPct);
        policy.setDeliveryCost(deliveryCost);
    }

    /**
     * Computes total income over a period.
     */
    public double computeTotalIncome(LocalDate from, LocalDate to) {
        return OrderRepository.getInstance().sumRevenue(from, to);
    }

    /**
     * Computes total profit over a period.
     */
    public double computeTotalProfit(LocalDate from, LocalDate to) {
        return OrderRepository.getInstance().sumProfit(from, to);
    }

    /**
     * Computes average income per active customer.
     */
    public double computeAverageIncomePerCustomer(LocalDate from, LocalDate to) {
        double income = computeTotalIncome(from, to);
        int customers = CustomerRepository.getInstance().countActiveBetween(from, to);
        return (customers > 0) ? income / customers : 0;
    }

    /**
     * Adjusts pricing to meet target profit.
     */
    public void meetTargetProfit(LocalDate from, LocalDate to, double targetProfit) {
        // example: simplistic proportion adjustment
        double currentProfit = computeTotalProfit(from, to);
        double factor = targetProfit / (currentProfit == 0 ? 1 : currentProfit);
        PricingPolicy policy = PricingPolicy.getInstance();
        policy.setMarkupPercentage(policy.getMarkupPercentage() * factor);
    }

    /**
     * Finds most or least selling restaurant.
     */
    public Restaurant findTopRestaurant(boolean most) {
        return RestaurantRepository.getInstance().findBySales(most);
    }

    /**
     * Finds most or least active courier.
     */
    public Courier findTopCourier(boolean most) {
        return CourierRepository.getInstance().findByDeliveries(most);
    }

    /**
     * Sets the delivery assignment policy.
     */
    public void setDeliveryPolicy(DeliveryPolicy policy) {
        DeliveryAssignmentService.getInstance().setPolicy(policy);
    }

    @Override
    public String toString() {
        return String.format("Manager[id=%s, name=%s %s]", id, firstName, lastName);
    }
}