package fr.cs.groupJ.myFoodora.model.menu;

import fr.cs.groupJ.myFoodora.model.Dish.Dish;

import java.util.List;
import java.util.ArrayList;

public class Menu {

    private List<Dish> dishes = new ArrayList<>();

    public Menu() {
    }

    // ===== Getters and Setters =====
    public List<Dish> getDishes() {
        return dishes;
    }
    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    // ===== Methods =====
    public void addDish(Dish dish) {
        dishes.add(dish);
    }
    public void removeDish(Dish dish) {
        dishes.remove(dish);
    }
}