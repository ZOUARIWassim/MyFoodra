package fr.cs.groupJ.myFoodora.model.meal;
import fr.cs.groupJ.myFoodora.model.Dish.Dish;
import fr.cs.groupJ.myFoodora.util.FoodType;
import java.util.List;
import java.util.Observer;
import java.util.Observable;

public class MealOfTheWeek extends Meal {
    private Meal baseMeal;

    public MealOfTheWeek(Meal baseMeal, List<FoodType> foodTypes) {
        super(foodTypes);
        this.baseMeal = baseMeal;
    }

    // ===== Getters and Setters =====

    public Meal getBaseMeal() {
        return baseMeal;
    }
    public void setBaseMeal(Meal baseMeal) {
        this.baseMeal = baseMeal;
    }

    // ===== Methods =====
    

    // ===== Overridden Methods from Meal Interface =====

    @Override
    public double getPrice() {
        double totalPrice = 0;
        for (Dish dish : baseMeal.getDishes()) {
            totalPrice += dish.getPrice();
        }
        return totalPrice * (1 - SPECIAL_DISCOUNT_FACTOR);
    }

    @Override
    public List<Dish> getDishes() {
        return baseMeal.getDishes();
    }

    @Override
    public String getTitle() {
        return "Meal of the Week: " + baseMeal.getTitle();
    }

}