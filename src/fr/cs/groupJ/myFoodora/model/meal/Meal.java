package fr.cs.groupJ.myFoodora.model.meal;

import fr.cs.groupJ.myFoodora.util.CustomObservable;
import fr.cs.groupJ.myFoodora.util.CustomObserver;
import fr.cs.groupJ.myFoodora.model.Dish.Dish;
import fr.cs.groupJ.myFoodora.model.item.Item;
import fr.cs.groupJ.myFoodora.util.FoodType;

import java.util.List;

public abstract class Meal implements CustomObserver, Item {

    protected List<FoodType> foodTypes;

    protected String name;
    protected double DISCOUNT_FACTOR = 0.05;
    protected double SPECIAL_DISCOUNT_FACTOR = 0.10;
    protected boolean isMealOfTheWeek = false;
    
    public Meal(String name,List<FoodType> foodTypes) {
        this.name = name;
        this.foodTypes = foodTypes;
    }

    // ===== Getters and Setters =====
    public List<FoodType> getFoodTypes() {
        return foodTypes;
    }
    public void setFoodTypes(List<FoodType> foodTypes) {
        this.foodTypes = foodTypes;
    }
    public String getName() {
        return name;
    }
    protected void setName(String name) {
        this.name = name;
    }
    public double getDiscountFactor() {
        if (isMealOfTheWeek) {
            return SPECIAL_DISCOUNT_FACTOR;
        }
        return DISCOUNT_FACTOR;
    }
    

    // ===== Abstracts Methods =====

    public abstract double getPrice();
    public abstract List<Dish> getDishes();
    public abstract String getTitle();

    // ===== Methods =====

    public boolean isGlutenFree() {
        return foodTypes.contains(FoodType.GLUTEN_FREE);
    }
    public boolean isStandard() {
        return foodTypes.contains(FoodType.STANDART);
    }
    public boolean isVegetarian() {
        return foodTypes.contains(FoodType.VEGETARIAN);
    }
    public void setMeatOfTheWeek() {
        if (isMealOfTheWeek) {
            throw new IllegalStateException("This meal is already set as Meal of the Week.");
        }
        this.isMealOfTheWeek = true;
    }
    public boolean isMealOfTheWeek() {
        return isMealOfTheWeek;
    }
    public void deleteMealOfTheWeek() {
        if (!isMealOfTheWeek) {
            throw new IllegalStateException("This meal is not set as Meal of the Week.");
        }
        this.isMealOfTheWeek = false;
    }
    
    // ===== CustomObserver Implementation =====

    @Override
    public void update(CustomObservable o, Object arg){
        Object[] args = (Object[]) arg;
        this.DISCOUNT_FACTOR = (double) args[0];
        this.SPECIAL_DISCOUNT_FACTOR = (double) args[1];
    }
}