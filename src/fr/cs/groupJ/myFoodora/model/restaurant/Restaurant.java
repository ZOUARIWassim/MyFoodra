package fr.cs.groupJ.myFoodora.model.restaurant;

import fr.cs.groupJ.myFoodora.model.user.User;
import fr.cs.groupJ.myFoodora.model.menu.Menu;
import fr.cs.groupJ.myFoodora.model.meal.Meal;
import fr.cs.groupJ.myFoodora.util.Coordinate;
import fr.cs.groupJ.myFoodora.util.CustomObservable;
import fr.cs.groupJ.myFoodora.util.CustomObserver;
import java.util.ArrayList;
import java.util.List;

public class Restaurant extends User implements CustomObservable {
    private Coordinate location;
    private Menu menu;
    private List<Meal> meals;
    private double discountFactor = 0.05; 
    private double specialDiscountFactor = 0.10;
    private ArrayList<CustomObserver> observers = new ArrayList<>();
    private boolean changed = false;

    public Restaurant(int id, String username, String password, Coordinate location, Menu menu, List<Meal> meals) {
        super(id, username, password);
        this.location = location;
        this.menu = menu;
        this.meals = meals;
        for (Meal meal : meals) {
            this.addObserver(meal);
        }
    }

    // ===== Getters and Setters =====

    public Coordinate getLocation() {
        return location;
    }
    public String getUsername() {
        return username;
    }
    public void setLocation(Coordinate location) {
        this.location = location;
    }
    public Menu getMenu() {
        return menu;
    }
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    public List<Meal> getMeals() {
        return meals;
    }
    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
    public double getDiscountFactor() {
        return discountFactor;
    }
    public void setDiscountFactor(double discountFactor) {
        this.discountFactor = discountFactor;
        setChanged();
        notifyObservers(new Object[]{discountFactor, specialDiscountFactor});
    }
    public double getSpecialDiscountFactor() {
        return specialDiscountFactor;
    }
    public void setSpecialDiscountFactor(double specialDiscountFactor) {
        this.specialDiscountFactor = specialDiscountFactor;
        setChanged();
        notifyObservers(new Object[]{discountFactor, specialDiscountFactor});
    }
    public Meal getMealOfTheWeek() {
    for (Meal meal : meals) {
        if (meal.isMealOfTheWeek()) {
            return meal;
        }
    }
    return null;
}

    // ===== User Interface Methods =====

    // ===== Methods =====

    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    public void removeMeal(Meal meal) {
        meals.remove(meal);
    }

    public void addMealOfTheWeek(Meal meal) {
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
    public void notifyObservers(Object arg) {
        if (!changed) {
            return;
        }
        for (CustomObserver observer : observers) {
            observer.update(this, arg);
        }
        changed = false; 
    }

}