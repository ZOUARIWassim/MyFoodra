package fr.cs.groupJ.myFoodora.model;

import fr.cs.groupJ.myFoodora.util.Coordinate;
import fr.cs.groupJ.myFoodora.util.DishCategory;
import fr.cs.groupJ.myFoodora.util.FoodType;
import fr.cs.groupJ.myFoodora.model.user.*;
import fr.cs.groupJ.myFoodora.model.Dish.Dish;
import fr.cs.groupJ.myFoodora.model.menu.Menu;
import fr.cs.groupJ.myFoodora.model.order.Order;
import fr.cs.groupJ.myFoodora.model.meal.*;
import fr.cs.groupJ.myFoodora.model.restaurant.Restaurant;
import fr.cs.groupJ.myFoodora.util.Date;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class sysModel {

	private static sysModel instance = null;
	private List<Customer> Customers = new ArrayList<>();
	private List<Courier> Couriers = new ArrayList<>();
	private final Map<String, User> userMap = new HashMap<>();
	private final Map<String, Restaurant> restaurantMap = new HashMap<>();
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
		restaurantMap.put(restaurant.getName(), restaurant);
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

	public void createMeal(String mealName) {
		Restaurant restaurant = (Restaurant) currentUser;
		if (restaurant.hasMeal(mealName)) {
			throw new IllegalArgumentException("Meal already exists.");
		}
		Meal meal = new BaseMeal(mealName, new ArrayList<>());
		restaurant.getMeals().add(meal);
	}

	public void addDishToMeal(String dishName, String mealName) {
		Restaurant restaurant = (Restaurant) currentUser;
		Meal meal = restaurant.getMeal(mealName);
		
		Dish dish = restaurant.getDish(dishName);
		
		if (meal.getDishes().contains(dish)) {
			throw new IllegalArgumentException("Dish already exists in the meal.");
		}
		
		meal.addDish(dish);
	}

	public void showMeal(String mealName) {
		Restaurant restaurant = (Restaurant) currentUser;
		Meal meal = restaurant.getMeal(mealName);
		
		if (meal == null) {
			throw new IllegalArgumentException("Meal not found.");
		}
		
		System.out.println("Meal Name: " + meal.getName());
		System.out.println("Dishes: ");
		for (Dish dish : meal.getDishes()) {
			System.out.println("- " + dish.getName() + " (" + dish.getCategory() + ")");
		}
		System.out.println("Price: " + meal.getPrice());
	}

	public void saveMeal(String mealName) {
		Restaurant restaurant = (Restaurant) currentUser;
		Meal meal = restaurant.getMeal(mealName);
		
		if (meal == null) {
			throw new IllegalArgumentException("Meal not found.");
		}
		
		List<Dish> dishes = meal.getDishes();
		List<DishCategory> dishCategoryies = new ArrayList<>();
		for (Dish dish : dishes) {
			dishCategoryies.add(dish.getCategory());
		}
		if (dishCategoryies.contains(DishCategory.STARTER) && 
			dishCategoryies.contains(DishCategory.MAIN_COURSE) && 
			dishCategoryies.contains(DishCategory.DESSERT)) {
			FullMeal newMeal = new FullMeal(mealName, 
				meal.getDishes().get(0), 
				meal.getDishes().get(1), 
				meal.getDishes().get(2), 
				meal.getFoodTypes());
			restaurant.getMeals().remove(meal);
			restaurant.getMeals().add(newMeal);
		} else if ((dishCategoryies.contains(DishCategory.MAIN_COURSE) && 
			dishCategoryies.contains(DishCategory.DESSERT)) ||
			(dishCategoryies.contains(DishCategory.STARTER) &&
			dishCategoryies.contains(DishCategory.MAIN_COURSE))) {
			HalfMeal newMeal = new HalfMeal(mealName, 
				meal.getDishes().get(0), 
				meal.getDishes().get(1), 
				meal.getFoodTypes());
			restaurant.getMeals().remove(meal);
			restaurant.getMeals().add(newMeal);
		} else {
			throw new IllegalArgumentException("Meal must contain at least a main dish and a dessert or a starter.");
		}
	}

	public void setMealOfTheWeek(String mealName) {
		Restaurant restaurant = (Restaurant) currentUser;
		Meal meal = restaurant.getMeal(mealName);
		
		if (meal == null) {
			throw new IllegalArgumentException("Meal not found.");
		}
		
		restaurant.setMealOfTheWeek(meal);
	}

	public void removeMealOfTheWeek(String mealName) {
		Restaurant restaurant = (Restaurant) currentUser;
		Meal meal = restaurant.getMeal(mealName);
		
		if (meal == null || !restaurant.getMealOfTheWeek().equals(meal)) {
			throw new IllegalArgumentException("Meal not found or is not the meal of the week.");
		}
		
		restaurant.removeMealOfTheWeek(meal);
	}

	// ===== Order Methods =====

	public void createOrder(String restaurantName, String orderName) {
		if (currentUser == null || !(currentUser instanceof Customer)) {
			throw new IllegalStateException("You must be logged in as a customer to create an order.");
		}
		Customer customer = (Customer) currentUser;
		Restaurant restaurant = (Restaurant) restaurantMap.get(restaurantName);
		if (restaurant == null ) {
			throw new IllegalArgumentException("Restaurant not found or is not a valid restaurant.");
		}
		customer.createOrder(restaurant, orderName);
	}

	public void addItemToOrder(String orderName, String dishName) {
		Customer customer = (Customer) currentUser;
		Order order = customer.getOrder(orderName);
		if (order == null) {
			throw new IllegalArgumentException("Order not found.");
		}
		Dish dish = order.getRestaurant().getDish(dishName);
		if (dish != null) {
			order.addItem(dish);
			return;
		}
		Meal meal = order.getRestaurant().getMeal(dishName);
		if (meal != null) {
			order.addItem(meal);
			return;
		}
		throw new IllegalArgumentException("Item not found in the restaurant's menu.");
	}
	public void endOrder(String orderName, String orderDate) {
		Customer customer = (Customer) currentUser;
		Order order = customer.getOrder(orderName);
		if (order == null) {
			throw new IllegalArgumentException("Order not found.");
		}
		if (order.isFinalized()) {
			throw new IllegalStateException("Order is already finalized.");
		}
		int day = Integer.parseInt(orderDate.substring(0, 2));
		int month = Integer.parseInt(orderDate.substring(3, 5));
		int year = Integer.parseInt(orderDate.substring(6, 10));
		String time = orderDate.substring(11, 16);
		Date orderDateObj = new Date(day, month, year, time);

		if (orderDateObj == null) {
			throw new IllegalArgumentException("Invalid order date format. Use dd/MM/yyyy HH:mm");
		}
		order.finalizeOrder(orderDateObj);
	}
	
		// ===== Courrier Methods =====

	public void setCourierOnDuty(String username) {
		Courier courier = (Courier) currentUser;
		if (!courier.getUsername().equals(username)) {
			throw new IllegalArgumentException("You can only set yourself on duty.");
		}
		courier.setOnDuty();
	}

	public void setCourierOffDuty(String username) {
		Courier courier = (Courier) currentUser;
		if (!courier.getUsername().equals(username)) {
			throw new IllegalArgumentException("You can only set yourself off duty.");
		}
		courier.setOffDuty();
	}
	
	// ===== Card Methods =====
	public void associateCard(String username, String RestaurantName, String cardType) {
		Manager manager = (Manager) currentUser;
		Customer customer = (Customer) userMap.get(username);
		Restaurant restaurant = restaurantMap.get(RestaurantName);
		if (customer == null) {
			throw new IllegalArgumentException("Customer not found.");
		}
		if (!customer.getFidelityCards().isEmpty()) {
			throw new IllegalStateException("Customer already has a fidelity card.");
		}
		manager.associateCard(restaurant, customer, cardType);
	}

	
	// ===== Authentication Methods =====

	public boolean login(String username, String password) {
		if (this.currentUser != null) {
			throw new IllegalStateException("A user is already logged in. Please log out first.");
		}
        User user = userMap.get(username);
        if (user != null && user.checkPassword(password)) {
            this.currentUser = user;
            return true;
        }
        return false;
    }
    public void logout() {
		if (this.currentUser == null) {
			throw new IllegalStateException("No user is currently logged in.");
		}
        this.currentUser = null;
    }


}
