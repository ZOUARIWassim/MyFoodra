package fr.cs.groupJ.myFoodora.model.order;

import fr.cs.groupJ.myFoodora.util.Date;
import fr.cs.groupJ.myFoodora.model.item.Item;
import fr.cs.groupJ.myFoodora.model.user.Customer;
import fr.cs.groupJ.myFoodora.model.fidelityCard.FidelityCard;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Date orderDate;
    private Customer customer;
    private List<Item> items = new ArrayList<>();

    public Order( Customer customer, Date orderDate) {
        this.orderDate = orderDate;
        this.customer = customer;
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

    // ===== Methods =====

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
        FidelityCard fidelityCard = customer.getFidelityCard();
        double basePrice = calculateBasePrice();
        if (fidelityCard != null) {
            return fidelityCard.computeFinalPrice(basePrice, customer);
        } else {
            throw new IllegalStateException("Customer does not have a fidelity card.");
        }
    }
}