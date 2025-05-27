package fr.cs.groupJ.myFoodora.model.meal;

import fr.cs.groupJ.myFoodora.model.Dish.Dish;
import fr.cs.groupJ.myFoodora.util.FoodType;

import java.util.List;
import java.util.ArrayList;


public class BaseMeal extends Meal {

    private List<Dish> dishes = new ArrayList<>();
    public BaseMeal(String name, List<FoodType> foodTypes) {
        super(name, foodTypes);
    }

    // ===== Overridden Methods from Meal SuperClass =====
    @Override
    public double getPrice() {
        return 10.0;
    }
    @Override
    public List<Dish> getDishes() {
        return dishes;
    }
    @Override
    public String getTitle() {
        return "Base Meal: " + getName();
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

}