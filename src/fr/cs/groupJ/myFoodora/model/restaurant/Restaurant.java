package fr.cs.groupJ.myFoodora.model.restaurant;

import fr.cs.groupJ.myFoodora.util.CustomObservable;
import fr.cs.groupJ.myFoodora.util.CustomObserver;
import fr.cs.groupJ.myFoodora.model.user.Customer;
import fr.cs.groupJ.myFoodora.model.meal.Meal;
import fr.cs.groupJ.myFoodora.model.menu.Menu;
import fr.cs.groupJ.myFoodora.model.user.User;
import fr.cs.groupJ.myFoodora.util.Coordinate;
import fr.cs.groupJ.myFoodora.util.Offer;
import fr.cs.groupJ.myFoodora.util.Role;

import java.util.ArrayList;
import java.util.List;

public class Restaurant extends User implements CustomObservable {

    private String name;
    private Menu menu;
    private List<Meal> meals;

    private List<Offer> offers;
    private List<Customer> subscribedCustomers = new ArrayList<>();
    private double discountFactor = 0.05; 
    private double specialDiscountFactor = 0.10;
    
    private ArrayList<CustomObserver> observers = new ArrayList<>();
    private boolean changed = false;


    public Restaurant( String name, String username, String password, Coordinate adress, Menu menu, List<Meal> meals) {
        super(username, password, adress);
        this.name = name;
        this.menu = menu;
        this.meals = meals;
        for (Meal meal : meals) {
            this.addObserver(meal);
        }
    }

    // ===== Getters and Setters =====

    public String getName() {
        return name;
    }
    protected void setName(String name) {
        this.name = name;
    }
    public List<Customer> getSubscribedCustomers() {
        return subscribedCustomers;
    }
    public void setSubscribedCustomers(List<Customer> customers) {
        this.subscribedCustomers = customers;
        for (Customer customer : customers) {
            this.addObserver(customer);
        }
    }
    public Menu getMenu() {
        return menu;
    }
    protected void setMenu(Menu menu) {
        this.menu = menu;
    }
    public List<Meal> getMeals() {
        return meals;
    }
    protected void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
    public double getDiscountFactor() {
        return discountFactor;
    }
    public void setDiscountFactor(double discountFactor) {
        this.discountFactor = discountFactor;
        setChanged();
        notifyObserversMeals(new Object[]{discountFactor, specialDiscountFactor});
    }
    public double getSpecialDiscountFactor() {
        return specialDiscountFactor;
    }
    public void setSpecialDiscountFactor(double specialDiscountFactor) {
        this.specialDiscountFactor = specialDiscountFactor;
        setChanged();
        notifyObserversMeals(new Object[]{discountFactor, specialDiscountFactor});
    }
    public List<Offer> getOffers() {
        return offers;
    }
    public void setOffers(List<Offer> offers) {
        this.offers = offers;
        setChanged();
        notifyObserversCustomers(offers);
    }

    // ===== Methods  =====

    public void subscribeCustomer(Customer customer) {
        if (customer != null && !subscribedCustomers.contains(customer)) {
            subscribedCustomers.add(customer);
            this.addObserver(customer);
            setChanged();
            notifyObserversCustomers(new Object[]{true, offers});
        }
    }
    public void unsubscribeCustomer(Customer customer) {
        if (customer != null && subscribedCustomers.contains(customer)) {
            subscribedCustomers.remove(customer);
            setChanged();
            notifyObserversCustomers(new Object[]{false, offers});
            this.deleteObserver(customer);
        }
    }
    public boolean isSubscribed(Customer customer) {
        return subscribedCustomers.contains(customer);
    }
    public void addMeal(Meal meal) {
        meals.add(meal);
    }
    public void removeMeal(Meal meal) {
        meals.remove(meal);
    }
    public void setMealOfTheWeek(Meal meal) {
        if (meal == null || !meals.contains(meal)) {
            throw new IllegalArgumentException("Meal must be part of the restaurant's meals.");
        }
        for (Meal m : meals) {
            if (m.isMealOfTheWeek()) {
                m.deleteMealOfTheWeek();
            }
        }
        meal.setMeatOfTheWeek();
    }
    public Meal getMealOfTheWeek() {
        for (Meal meal : meals) {
            if (meal.isMealOfTheWeek()) {
                return meal;
            }
        }
        return null;
    }

    // ===== Overridden Methods from CustomObservable =====

    @Override
    public void addObserver(CustomObserver observer) {
        observers.add(observer);
    }
    @Override
    public void deleteObserver(CustomObserver observer) {
        observers.remove(observer);
    }
    @Override
    public void setChanged() {
        this.changed = true;
    }
    @Override
    public void notifyObserversMeals(Object arg) {
        if (!changed) {
            return;
        }
        for (CustomObserver observer : observers) {
            if (observer instanceof Meal) {
                observer.update(this, arg);
            }
        }
        changed = false; 
    }
    @Override
    public void notifyObserversCustomers(Object arg) {
        if (!changed) {
            return;
        }
        for (CustomObserver observer : observers) {
            if (observer instanceof User) {
                observer.update(this, arg);
            }
        }
        changed = false; 
    }

    // ===== Overridden Methods from User =====

    @Override
    public Role getRole() {
        return Role.RESTAURANT;
    }
}