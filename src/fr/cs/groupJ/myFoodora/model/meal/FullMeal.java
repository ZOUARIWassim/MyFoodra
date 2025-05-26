package fr.cs.groupJ.myFoodora.model.meal;

import fr.cs.groupJ.myFoodora.model.Dish.Dish;
import fr.cs.groupJ.myFoodora.util.FoodType;

import java.util.List;

public class FullMeal extends Meal {

    private Dish starter;
    private Dish main;
    private Dish dessert;

    public FullMeal(String name,Dish starter, Dish main, Dish dessert, List<FoodType> foodTypes) {
        super(name,foodTypes);
        this.starter = starter;
        this.main = main;
        this.dessert = dessert;
    }

    // ===== Getters and Setters =====

    public Dish getStarter() {
        return starter;
    }
    public void setStarter(Dish starter) {
        this.starter = starter;
    }
    public Dish getMain() {
        return main;
    }
    public void setMain(Dish main) {
        this.main = main;
    }
    public Dish getDessert() {
        return dessert;
    }
    public void setDessert(Dish dessert) {
        this.dessert = dessert;
    }

    // ===== Methods =====

    // ===== Overridden Methods from Meal SuperClass =====

    @Override
    public double getPrice() {
        double totalPrice = starter.getPrice() + main.getPrice() + dessert.getPrice();
        if (this.isMealOfTheWeek()) {
            return totalPrice * (1 - SPECIAL_DISCOUNT_FACTOR);
        }
        else {
            return totalPrice * (1 - DISCOUNT_FACTOR);
        }
    }
    @Override
    public List<Dish> getDishes() {
        return List.of(starter, main, dessert);
    }
    @Override
    public String getTitle() {
        return "Full Meal: " + starter.getName() + ", " + main.getName() + ", and " + dessert.getName();
    }
}
