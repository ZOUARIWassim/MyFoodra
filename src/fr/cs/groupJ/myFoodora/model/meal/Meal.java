package fr.cs.groupJ.myFoodora.model.meal;
import fr.cs.groupJ.myFoodora.model.Dish.Dish;
import fr.cs.groupJ.myFoodora.util.FoodType;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public abstract class Meal implements Observer {
    protected int id;
    protected static int globalId = 0;
    protected String name;
    protected List<FoodType> foodTypes;
    protected static double DISCOUNT_FACTOR = 0.05;
    protected static double SPECIAL_DISCOUNT_FACTOR = 0.10;
    
    public Meal(List<FoodType> foodTypes) {
        this.id = globalId;
        this.globalId= globalId + 1;
        this.name = "Meal " + id;
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