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

    private FidelityCard fidelityCard = null;
    private int points = 0;
    private Order currentOrder = null;
    private List<Offer> notifs = new ArrayList<>();

    public Customer(String username, String password, String firstName, String lastName, Coordinate address, List<ContactInfo> contactInfo) {
        super(username, password, address);
        super.setContactInfos(contactInfo);
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
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public FidelityCard getFidelityCard() {
        return fidelityCard;
    }
    public void setFidelityCard(FidelityCard fidelityCard) {
        this.fidelityCard = fidelityCard;
    }
    public Order getCurrentOrder() {
        return currentOrder;
    }
    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    // ===== Methods =====

    public void placeOrder(Item item) {
        Date orderDate = new Date(11, 18, 2022, "12:30");
        List<Item> items = new ArrayList<>();
        items.add(item);
        this.currentOrder = new Order(this, orderDate, items);
    }
    public void addItemToOrder(Item item) {
        if (this.currentOrder != null) {
            this.currentOrder.addItem(item);
        } else {
            throw new IllegalStateException("No current order to add items to.");
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
        if (isSubscribed) {
            this.notifs.addAll(offers);
        } else {
            for (Offer offer : offers) {
                this.notifs.remove(offer);
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
