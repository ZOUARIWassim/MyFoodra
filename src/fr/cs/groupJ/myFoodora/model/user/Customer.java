package fr.cs.groupJ.myFoodora.model.user;

import fr.cs.groupJ.myFoodora.model.fidelityCard.BasicFidelityCard;
import fr.cs.groupJ.myFoodora.model.fidelityCard.FidelityCard;
import fr.cs.groupJ.myFoodora.model.restaurant.Restaurant;
import fr.cs.groupJ.myFoodora.util.CustomObservable;
import fr.cs.groupJ.myFoodora.util.CustomObserver;
import fr.cs.groupJ.myFoodora.model.order.Order;
import fr.cs.groupJ.myFoodora.util.ContactInfo;
import fr.cs.groupJ.myFoodora.model.item.Item;
import fr.cs.groupJ.myFoodora.model.user.User;
import fr.cs.groupJ.myFoodora.util.Coordinate;
import fr.cs.groupJ.myFoodora.util.Offer;
import fr.cs.groupJ.myFoodora.util.Role;
import fr.cs.groupJ.myFoodora.util.Date;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User implements CustomObserver {

    private String firstName;
    private String lastName;

    private List<FidelityCard> fidelityCards = new ArrayList<>();
    private int points = 0;
    private List<Order> foodCart = new ArrayList<>();
    private List<Offer> notifs = new ArrayList<>();
    private List<List<Order>> orderHistory = new ArrayList<>();

    public Customer(String username, String password, String firstName, String lastName, Coordinate address) {
        super(username, password, address);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // ===== Getters and Setters =====
    public String getFirstName() {
        return firstName;
    }
    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public List<Offer> getNotifs() {
        return notifs;
    }
    public void removeOffer(Offer offer) {
        if (notifs.contains(offer)) {
            notifs.remove(offer);
        } else {
            throw new IllegalArgumentException("Offer not found in notifications.");
        }
    }
    public void addOffer(Offer offer) {
        if (offer != null && !notifs.contains(offer)) {
            notifs.add(offer);
        } else {
            throw new IllegalArgumentException("Offer is null or already exists in notifications.");
        }
    }
    public List<List<Order>> getOrderHistory() {
        return orderHistory;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public List<FidelityCard> getFidelityCards() {
        return fidelityCards;
    }
    public void addFidelityCard(FidelityCard card) {
        if (card != null && !fidelityCards.contains(card)) {
            fidelityCards.add(card);
        } else {
            throw new IllegalArgumentException("Fidelity card is null or already exists.");
        }
    }
    public FidelityCard getFidelityCard(Restaurant restaurant) {
        for (FidelityCard card : fidelityCards) {
            if (card.getRestaurant().equals(restaurant)) {
                return card;
            }
        }
        throw new IllegalArgumentException("No fidelity card found for the specified restaurant.");
    }
    public List<Order> getFoodCart() {
        return foodCart;
    }
    public Order getOrder(String orderName) {
        for (Order order : foodCart) {
            if (order.getOrderName().equals(orderName)) {
                return order;
            }
        }
        throw new IllegalArgumentException("Order not found in the cart.");
    }
    public void setFoodCart(List<Order> foodCart) {
        this.foodCart = foodCart;
    }

    // ===== Methods =====
    public void createOrder(Restaurant restaurant, String orderName) {
        List<Item> items = new ArrayList<>();
        this.foodCart.add(new Order(orderName, this, restaurant));
    }
    public void addToFoodCart(Order order) {
        if (order != null && !foodCart.contains(order)) {
            foodCart.add(order);
        } else {
            throw new IllegalArgumentException("Order is null or already in the cart.");
        }
    }
    public void finalizeOrder() {
        if (foodCart.isEmpty()) {
            throw new IllegalStateException("Cannot finalize an empty food cart.");
        }
        this.orderHistory.add(foodCart);
        this.foodCart.clear();
    }
    public void removeFromFoodCart(Order order) {
        if (order != null && foodCart.contains(order)) {
            foodCart.remove(order);
        } else {
            throw new IllegalArgumentException("Order is null or not in the cart.");
        }
    }
    public void resetPoints() {
        this.points = 0;
    }
    public void subscribeRestaurant(Restaurant restaurant) {
        if (restaurant.isSubscribed(this)) {
            System.out.println("You are already subscribed to this restaurant.");
        } else {
            restaurant.subscribeCustomer(this);
            System.out.println("You have successfully subscribed to " + restaurant.getName() + ".");        }
    }
    public void unsubscribeRestaurant(Restaurant restaurant) {
        if (restaurant.isSubscribed(this)) {
            restaurant.unsubscribeCustomer(this);
            System.out.println("You have successfully unsubscribed from " + restaurant.getName() + ".");
        } else {
            System.out.println("You are not subscribed to " + restaurant.getName() + ".");
        }
    }
    public void enableNotifications(Restaurant restaurant) {
        if (restaurant.isSubscribed(this)) {
            System.out.println("Notifications enabled for " + restaurant.getName() + ".");
        } else {
            this.subscribeRestaurant(restaurant);
            System.out.println("You were not subscribed to " + restaurant.getName() + ". Now you are, and notifications are enabled.");
        }
    }
    public void disableNotifications(Restaurant restaurant) {
        if (restaurant.isSubscribed(this)) {
            this.unsubscribeRestaurant(restaurant);
            System.out.println("Notifications disabled for " + restaurant.getName() + ".");
        } else {
            System.out.println("You are not subscribed to " + restaurant.getName() + ". No notifications to disable.");
        }
    }

    // ===== Overridden Methods from User =====
    @Override
    public Role getRole() {
        return Role.CUSTOMER;
    }
    
    // ===== Overridden Methods from CustomObserver =====
    @Override
    public void update(CustomObservable o, Object arg) {
        Object[] args = (Object[]) arg;
        boolean isSubscribed = (boolean) args[0];
        List<Offer> offers = (List<Offer>) args[1];
        if (offers != null && !offers.isEmpty()) {
            if (isSubscribed) {
                for (Offer offer : offers) {
                    if (!this.notifs.contains(offer)) {
                        this.notifs.add(offer);
                    }
                }
            } else {
                for (Offer offer : offers) {
                    this.notifs.remove(offer);
                }
            }
        } 
    }

    @Override
    public String toString() {
        return String.format(
            "Customer [id=%d, username=%s, firstName=%s, lastName=%s]",
            getId(), getUsername(), firstName, lastName
        );
    }
}
