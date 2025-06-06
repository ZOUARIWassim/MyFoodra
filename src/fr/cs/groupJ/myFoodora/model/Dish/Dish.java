package fr.cs.groupJ.myFoodora.model.Dish;

import fr.cs.groupJ.myFoodora.util.DishCategory;
import fr.cs.groupJ.myFoodora.model.item.Item;
import fr.cs.groupJ.myFoodora.util.FoodType;

import java.util.List;

public class Dish implements Item {
    
    private String name;
    private double price;
    private DishCategory category;
    private List<FoodType> types;

    public Dish(String name, DishCategory category, List<FoodType> types, double price) {
        this.name = name;
        this.category = category;
        this.types = types;
        this.price = price;
    }

    // ===== Getters and Setters =====
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public DishCategory getCategory() {
        return category;
    }
    public void setCategory(DishCategory category) {
        this.category = category;
    }
    public List<FoodType> getTypes() {
        return types;
    }
    public void setTypes(List<FoodType> types) {
        this.types = types;
    }
    @Override
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    // ===== Methods =====

    public boolean isGlutenFree() {
        return types.contains(FoodType.GLUTEN_FREE);
    }
    public boolean isStandard() {
        return types.contains(FoodType.STANDARD);
    }
    public boolean isVegetarian() {
        return types.contains(FoodType.VEGETARIAN);
    }

}
