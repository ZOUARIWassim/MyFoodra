package fr.cs.groupJ.myFoodora.model.meal;

import fr.cs.groupJ.myFoodora.model.Dish.Dish;
import fr.cs.groupJ.myFoodora.util.FoodType;

import java.util.List;

public class HalfMeal extends Meal {

    private Dish main;
    private Dish extra;

    public HalfMeal(String name,Dish main, Dish extra, List<FoodType> foodTypes) {
        super(name,foodTypes);
        this.main = main;
        this.extra = extra;
    }

    // ===== Getters and Setters =====
    public Dish getMain() {
        return main;
    }
    public void setMain(Dish main) {
        this.main = main;
    }
    public Dish getExtra() {
        return extra;
    }
    public void setExtra(Dish extra) {
        this.extra = extra;
    }


    // ===== Overridden Methods from Meal Interface =====
    @Override
    public double getPrice() {
        double totalPrice = main.getPrice() + extra.getPrice();
        if (this.isMealOfTheWeek()) {
            return totalPrice * (1 - SPECIAL_DISCOUNT_FACTOR);
        }
        else {
            return totalPrice * (1 - DISCOUNT_FACTOR);
        }
    }
    @Override
    public List<Dish> getDishes() {
        return List.of(main, extra);
    }
    @Override
    public void addDish(Dish dish) {
        throw new UnsupportedOperationException("Cannot add dishes to a HalfMeal");
    }
    @Override
    public String getTitle() {
        return "Half Meal: " + main.getName() + " and " + extra.getName();
    }
}