package fr.cs.groupJ.myFoodora.test;

import fr.cs.groupJ.myFoodora.model.Dish.Dish;
import fr.cs.groupJ.myFoodora.util.DishCategory;
import fr.cs.groupJ.myFoodora.model.meal.FullMeal;
import fr.cs.groupJ.myFoodora.model.meal.HalfMeal;
import fr.cs.groupJ.myFoodora.model.meal.Meal;
import fr.cs.groupJ.myFoodora.model.meal.MealOfTheWeek;
import fr.cs.groupJ.myFoodora.model.menu.Menu;
import fr.cs.groupJ.myFoodora.util.Coordinate;
import fr.cs.groupJ.myFoodora.model.restaurant.Restaurant;
import fr.cs.groupJ.myFoodora.util.FoodType;
import java.util.List;


public class Test {
    public static void main(String[] args) {
        Coordinate coordinate = new Coordinate(48.8566, 2.3522);

        Dish dish1 = new Dish("Salad", DishCategory.STARTER , List.of(FoodType.VEGETARIAN,FoodType.STANDART), 7.99);
        Dish dish2 = new Dish("Steak", DishCategory.MAIN_COURSE, List.of(FoodType.STANDART), 14.99);
        Dish dish3 = new Dish("Ice Cream", DishCategory.DESSERT, List.of(FoodType.GLUTEN_FREE,FoodType.STANDART), 5.49);

        FullMeal fullMeal = new FullMeal(dish1, dish2, dish3, List.of(FoodType.STANDART));
        HalfMeal halfMeal = new HalfMeal(dish1, dish2, List.of(FoodType.STANDART));
        MealOfTheWeek mealOfTheWeek = new MealOfTheWeek(fullMeal, List.of(FoodType.STANDART));

        Menu menu = new Menu(List.of(dish1, dish2, dish3));

        List<Meal> meals = List.of(fullMeal, halfMeal, mealOfTheWeek);
        Restaurant restaurant = new Restaurant(1,"Le Gourmet", "pwd", coordinate, menu, meals);

        System.out.println("Restaurant Name: " + restaurant.getUsername());
        System.out.println("Location: " + restaurant.getLocation());
        System.out.println("Menu Dishes:");
        for (Dish dish : restaurant.getMenu().getDishes()) {
            System.out.println("- " + dish.getName() + " (" + dish.getCategory() + "): $" + dish.getPrice());
        }
        System.out.println("Full Meal Price: $" + fullMeal.getPrice());
        System.out.println("Half Meal Price: $" + halfMeal.getPrice());
        System.out.println("Meal of the Week Price: $" + mealOfTheWeek.getPrice());
        System.out.println("Full Meal Dishes: ");
        for (Dish dish : fullMeal.getDishes()) {
            System.out.println("- " + dish.getName() + " (" + dish.getCategory() + ")");
        }
        System.out.println("Half Meal Dishes: ");
        for (Dish dish : halfMeal.getDishes()) {
            System.out.println("- " + dish.getName() + " (" + dish.getCategory() + ")");
        }
        System.out.println("Meal of the Week Dishes: ");
        for (Dish dish : mealOfTheWeek.getDishes()) {
            System.out.println("- " + dish.getName() + " (" + dish.getCategory() + ")");
        }
        System.out.println("Full Meal Title: " + fullMeal.getTitle());
        System.out.println("Half Meal Title: " + halfMeal.getTitle());
        System.out.println("Meal of the Week Title: " + mealOfTheWeek.getTitle());
        System.out.println("Is Full Meal Gluten Free? " + fullMeal.isGlutenFree());
        System.out.println("Is Half Meal Gluten Free? " + halfMeal.isGlutenFree());
        System.out.println("Is Meal of the Week Gluten Free? " + mealOfTheWeek.isGlutenFree());
        System.out.println("Is Full Meal Standard? " + fullMeal.isStandart());
        System.out.println("Is Half Meal Standard? " + halfMeal.isStandart());
        System.out.println("Is Meal of the Week Standard? " + mealOfTheWeek.isStandart());
        System.out.println("Is Full Meal Vegetarian? " + fullMeal.isVegetarian());
        System.out.println("Is Half Meal Vegetarian? " + halfMeal.isVegetarian());
        System.out.println("Is Meal of the Week Vegetarian? " + mealOfTheWeek.isVegetarian());

        // Test the observer pattern
       
        System.out.println("Discount Factor for Full Meal: " + fullMeal.getDiscountFactor());
        System.out.println("Discount Factor for Half Meal: " + halfMeal.getDiscountFactor());
        System.out.println("Discount Factor for Meal of the Week: " + mealOfTheWeek.getDiscountFactor());

        System.out.println("Restaurant Discount Factor: " + restaurant.getDiscountFactor());
        System.out.println("Restaurant Special Discount Factor: " + restaurant.getSpecialDiscountFactor());

        restaurant.setDiscountFactor(0.2);
        System.out.println("Updated Restaurant Discount Factor: " + restaurant.getDiscountFactor());
        restaurant.setSpecialDiscountFactor(0.3);
        System.out.println("Updated Restaurant Special Discount Factor: " + restaurant.getSpecialDiscountFactor());

        System.out.println("Discount Factor for Full Meal: " + fullMeal.getDiscountFactor());
        System.out.println("Discount Factor for Half Meal: " + halfMeal.getDiscountFactor());
        System.out.println("Discount Factor for Meal of the Week: " + mealOfTheWeek.getSpecialDiscountFactor());

        System.out.println("Full Meal Price: $" + fullMeal.getPrice());
        System.out.println("Half Meal Price: $" + halfMeal.getPrice());
        System.out.println("Meal of the Week Price: $" + mealOfTheWeek.getPrice());

    }
}

