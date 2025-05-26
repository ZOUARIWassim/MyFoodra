package fr.cs.groupJ.myFoodora.model;

import fr.cs.groupJ.myFoodora.util.Coordinate;
import fr.cs.groupJ.myFoodora.util.DishCategory;
import fr.cs.groupJ.myFoodora.util.FoodType;
import fr.cs.groupJ.myFoodora.model.user.*;
import fr.cs.groupJ.myFoodora.model.Dish.Dish;
import fr.cs.groupJ.myFoodora.model.menu.Menu;
import fr.cs.groupJ.myFoodora.model.meal.*;
import fr.cs.groupJ.myFoodora.model.restaurant.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class sysModel {

	private static sysModel instance = null;
	private List<Customer> Customers = new ArrayList<>();
	private List<Courier> Couriers = new ArrayList<>();
	private final Map<String, User> userMap = new HashMap<>();
	private User currentUser;
	
	private sysModel(List<Customer> Customers) {
		this.Customers = Customers;
        Manager ceo = new Manager("ceo", "123456789", "Default", "Manager");
        userMap.put(ceo.getUsername(), ceo);
		
	}

	public static sysModel getInstance() {
		if (instance == null) {
			instance = new sysModel(new ArrayList<>());
		}
		return instance;
	}
	
	// ===== Getters and Setters =====

	public List<Customer> getCustomers() {
		return Customers;
	}
	protected void setCustomers(List<Customer> customers) {
		Customers = customers;
	}
	public User getCurrentUser() {
        return currentUser;
    }
	public List<Courier> getCouriers() {
		return Couriers;
	}
	public void setCouriers(List<Courier> couriers) {
		Couriers = couriers;
	}
	public Map<String, User> getUserMap() {
		return userMap;
	}


	// ===== Methods =====

	public void addCustomer(String firstName,String lastName,String username, double longitude,double latitude,String password) {
		if (userMap.containsKey(username)) {
            throw new IllegalArgumentException("Username already exists.");
        }
		Coordinate address = new Coordinate(longitude,latitude);
        Customer customer = new Customer(username, password, firstName, lastName, address);
        userMap.put(customer.getUsername(), customer);
		this.Customers.add(customer);
	}
	public void addCourier(Courier courier) {
	    if (userMap.containsKey(courier.getUsername())) {
	        throw new IllegalArgumentException("Username already exists.");
	    }
	    userMap.put(courier.getUsername(), courier);
	    this.Couriers.add(courier);
	}

	// ===== Restaurant Methods =====

	public void addRestaurant(String name, Coordinate adress, String username, String password) {
		if (userMap.containsKey(username)) {
			throw new IllegalArgumentException("Username already exists.");
		}
		Restaurant restaurant = new Restaurant(name, username, password, adress);
		userMap.put(restaurant.getUsername(), restaurant);
	}
	public void addDish(String dishName, String dishCategory, String foodType, double unitPrice){
		Restaurant restaurant = (Restaurant) currentUser;
		DishCategory dishCategoryEnum;
		try {
			dishCategoryEnum = DishCategory.valueOf(dishCategory.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid dish category. Dish categories are: " + DishCategory.getAllCategories());
		}
		FoodType foodTypeEnum;
		try {
			foodTypeEnum = FoodType.valueOf(foodType.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid food type. Food types are: " + FoodType.getAllTypes());
		}
		Dish dish = new Dish(dishName, dishCategoryEnum, List.of(foodTypeEnum), unitPrice);
		restaurant.getMenu().addDish(dish);
	}
	// public void createMeal(String mealName) {
	// 	Restaurant restaurant = (Restaurant) currentUser;
	// 	if (restaurant.hasMeal(mealName)) {
	// 		throw new IllegalArgumentException("Meal already exists.");
	// 	}
	// 	Meal meal = new Meal(mealName, new ArrayList<>());
	// 	restaurant.getMeals().add(meal);
	// }
	
	
	// ===== Authentication Methods =====

	public boolean login(String username, String password) {
        User user = userMap.get(username);
        if (user != null && user.checkPassword(password)) {
            this.currentUser = user;
            return true;
        }
        return false;
    }
    public void logout() {
        this.currentUser = null;
    }
}
