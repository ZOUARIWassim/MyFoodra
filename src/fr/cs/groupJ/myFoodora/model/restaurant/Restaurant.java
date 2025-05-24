package fr.cs.groupJ.myFoodora.model.restaurant;

import fr.cs.groupJ.myFoodora.model.user.User;
import fr.cs.groupJ.myFoodora.model.menu.Menu;
import fr.cs.groupJ.myFoodora.model.meal.Meal;
import fr.cs.groupJ.myFoodora.util.Coordinate;
import java.util.Observable;
import java.util.List;

public class Restaurant extends Observable {
    private Coordinate location;
    private Menu menu;
    private List<Meal> meals;
    private double discountFactor = 0.05; 
    private double specialDiscountFactor = 0.10;
    private int id;
    private String username;
    private String password;

    public Restaurant(int id, String username, String password, Coordinate location, Menu menu, List<Meal> meals) {
        this.id = id;
        this.username = username;
        this.password = password;
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

    // ===== User Interface Methods =====

    public void addMeal(Meal meal) {
        meals.add(meal);
        setChanged();
        notifyObservers();
    }

    public void removeMeal(Meal meal) {
        meals.remove(meal);
        setChanged();
        notifyObservers();
    }

}