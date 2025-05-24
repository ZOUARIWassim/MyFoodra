package fr.cs.groupJ.myFoodora.model.meal;
import fr.cs.groupJ.myFoodora.model.Dish.Dish;

import java.util.List;
import fr.cs.groupJ.myFoodora.util.FoodType;
import java.util.Observer;
import java.util.Observable;

public abstract class Meal implements Observer {
    protected List<FoodType> foodTypes;
    protected double DISCOUNT_FACTOR = 0.05;
    protected double SPECIAL_DISCOUNT_FACTOR = 0.10;
    
    public Meal(List<FoodType> foodTypes) {
        this.foodTypes = foodTypes;
    }

    // ===== Getters and Setters =====
    public List<FoodType> getFoodTypes() {
        return foodTypes;
    }
    public void setFoodTypes(List<FoodType> foodTypes) {
        this.foodTypes = foodTypes;
    }
    public double getDiscountFactor() {
        return DISCOUNT_FACTOR;
    }
    public double getSpecialDiscountFactor() {
        return SPECIAL_DISCOUNT_FACTOR;
    }

    // ===== Methods =====
    public abstract double getPrice();
    public abstract List<Dish> getDishes();
    public abstract String getTitle();

    public boolean isGlutenFree() {
        return foodTypes.contains(FoodType.GLUTEN_FREE);
    }
    public boolean isStandart() {
        return foodTypes.contains(FoodType.STANDART);
    }
    public boolean isVegetarian() {
        return foodTypes.contains(FoodType.VEGETARIAN);
    }

    @Override
    public void update(Observable o, Object arg){
        Object[] args = (Object[]) arg;
        this.DISCOUNT_FACTOR = (double) args[0];
        this.SPECIAL_DISCOUNT_FACTOR = (double) args[1];
    }
}