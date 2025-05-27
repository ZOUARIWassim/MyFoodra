package fr.cs.groupJ.myFoodora.controller;

import fr.cs.groupJ.myFoodora.model.restaurant.Restaurant;
import fr.cs.groupJ.myFoodora.util.AccessControl;
import fr.cs.groupJ.myFoodora.main.FileRunner;
import fr.cs.groupJ.myFoodora.util.Coordinate;
import fr.cs.groupJ.myFoodora.model.SysModel;
import fr.cs.groupJ.myFoodora.model.user.*;
import fr.cs.groupJ.myFoodora.util.Role;
import fr.cs.groupJ.myFoodora.view.SysViewer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class SysController {

	private SysModel model;
    private SysViewer view;

    public SysController(SysModel model, SysViewer view) {
        this.model = model;
        this.view = view;
    }

    // === Main methods ===

    private String[] parseArgs(String input) {
        List<String> args = new ArrayList<>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(input);
        while (m.find()) {
            args.add(m.group(1).replace("\"", ""));
        }
        return args.toArray(new String[0]);
    }
    public void processCommand(String commandLine) {
        
        String[] tokens = parseArgs(commandLine);
        if (tokens.length == 0) {
            view.showError("No command entered.");
            return;
        }
        String command = tokens[0];
        try {
            switch (command) {
                /// === Manager commands ===
                case "registerCustomer":
                	if (AccessControl.roleControl(Role.MANAGER, model)) {
                        handleRegisterCustomer(tokens);
                    } else {
                        view.showError("Access denied. This command requires role: " + Role.MANAGER);
                    }
                    break;
                case "registerCourier":
                    if (AccessControl.roleControl(Role.MANAGER, model)) {
                        handleRegisterCourier(tokens);
                    } else {
                        view.showError("Access denied. This command requires role: " + Role.MANAGER);
                    }
                    break;
                case "registerRestaurant":
                    if (AccessControl.roleControl(Role.MANAGER, model)) {
                        handleRegisterRestaurant(tokens);
                    } else {
                        view.showError("Access denied. This command requires role: " + Role.MANAGER);
                    }
                    break;
                                case "associateCard":
                    if (AccessControl.roleControl(Role.MANAGER, model)) {
                        handleAssociateCard(tokens);
                    } else {
                        view.showError("Access denied. This command requires role: " + Role.MANAGER);
                    }
                    break;
                case "showMenuItem":
                    if (AccessControl.roleControl(Role.MANAGER, model)) {
                        handleShowMenuItem(tokens);
                    } else {
                        view.showError("Access denied. This command requires role: " + Role.MANAGER);
                    }
                    break;
                case "showCustomers":
                    if (AccessControl.roleControl(Role.MANAGER, model)) {
                        handleShowCustomers(tokens);
                    } else {
                        view.showError("Access denied. This command requires role: " + Role.MANAGER);
                    }
                    break;
                /// === Restaurant commands ===
                case "registerDishRestaurant":
                    if (AccessControl.roleControl(Role.RESTAURANT, model)) {
                        handleAddDishRestaurant(tokens);
                    } else {
                        view.showError("Access denied. This command requires role: " + Role.RESTAURANT);
                    }
                    break;
                case "addDishRestaurantMenu":
                    if (AccessControl.roleControl(Role.RESTAURANT, model)) {
                        handleAddDishRestaurant(tokens);
                    } else {
                        view.showError("Access denied. This command requires role: " + Role.RESTAURANT);
                    }
                    break;
                case "createMeal":
                    if (AccessControl.roleControl(Role.RESTAURANT, model)) {
                        handlCreateMeal(tokens);
                    } else {
                        view.showError("Access denied. This command requires role: " + Role.RESTAURANT);
                    }
                    break;
                case "addDish2Meal":
                    if (AccessControl.roleControl(Role.RESTAURANT, model)) {
                        handleAddDish2Meal(tokens);
                    } else {
                        view.showError("Access denied. This command requires role: " + Role.RESTAURANT);
                    }
                    break;
                case "showMeal":
                    if (AccessControl.roleControl(Role.RESTAURANT, model)) {
                        handleShowMeal(tokens);
                    } else {
                        view.showError("Access denied. This command requires role: " + Role.RESTAURANT);
                    }
                    break;
                case "saveMeal":
                    if (AccessControl.roleControl(Role.RESTAURANT, model)) {
                        handleSaveMeal(tokens);
                    } else {
                        view.showError("Access denied. This command requires role: " + Role.RESTAURANT);
                    }
                    break;
                case "setSpecialOffer":
                    if (AccessControl.roleControl(Role.RESTAURANT, model)) {
                        handleSetSpecialOffer(tokens);
                    } else {
                        view.showError("Access denied. This command requires role: " + Role.RESTAURANT);
                    }
                    break;
                case "removeFromSpecialOffer":
                    if (AccessControl.roleControl(Role.RESTAURANT, model)) {
                        handleRemoveFromSpecialOffer(tokens);
                    } else {
                        view.showError("Access denied. This command requires role: " + Role.RESTAURANT);
                    }
                    break;
                /// === Customer commands ===
                case "createOrder":
                    if (AccessControl.roleControl(Role.CUSTOMER, model)) {
                        handleCreateOrder(tokens);
                    } else {
                        view.showError("Access denied. This command requires role: " + Role.CUSTOMER);
                    }
                    break;
                case "addItem2Order":
                    if (AccessControl.roleControl(Role.CUSTOMER, model)) {
                        handleAddItem2Order(tokens);
                    } else {
                        view.showError("Access denied. This command requires role: " + Role.CUSTOMER);
                    }
                    break;
                case "endOrder":
                    if (AccessControl.roleControl(Role.CUSTOMER, model)) {
                        handleEndOrder(tokens);
                    } else {
                        view.showError("Access denied. This command requires role: " + Role.CUSTOMER);
                    }
                    break;
                /// === Courier commands ===
                case "onDuty":
                    if (AccessControl.roleControl(Role.COURIER, model)) {
                        handleOnDutyCourier(tokens);
                    } else {
                        view.showError("Access denied. This command requires role: " + Role.COURIER);
                    }
                    break;
                case "offDuty":
                    if (AccessControl.roleControl(Role.COURIER, model)) {
                        handleOffDutyCourier(tokens);
                    } else {
                        view.showError("Access denied. This command requires role: " + Role.COURIER);
                    }
                    break;
                /// === General commands ===
                case "login":
                    handleLogin(tokens);
                    break;
                case "logout":
                    handleLogout();
                    break;
                case "whoami":
                    handleWhoAmI();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                case "help":
                    view.showHelp();
                    break;
                case "runTest":
                    handleRunTest(tokens);                    
                    break;
                default:
                    view.showError("Unknown command: " + command);
            }
        } catch (Exception e) {
            view.showError("Command failed: " + e.getMessage());
        }
    }

    // === General commands handlers ===
    private void handleLogin(String[] args) {
        if (args.length < 3) {
            view.showError("Usage: login <username> <password>");
            return;
        }
        String username = args[1];
        String password = args[2];
        if (model.login(username, password)) {
            view.showMessage("Login successful for: " + username);
        } else {
            view.showError("Login failed.");
        }
    }
    private void handleLogout() {
        if (model.getCurrentUser() != null) {
            model.logout();
            view.showMessage("Logout successful.");
        } else {
            view.showError("No user is currently logged in.");
        }
    }
    private void handleWhoAmI() {
        User currentUser = model.getCurrentUser();
        if (currentUser != null) {
            view.showMessage("Current user: " + currentUser.getUsername() + " (" + currentUser.getRole() + ")");
        } else {
            view.showError("No user is currently logged in.");
        }
    }
    private void handleRunTest(String[] args) {
        if (args.length < 2) {
            view.showError("Usage: runTest <fileName>");
            return;
        }
        String fileName = args[1];
        try {
            FileRunner.main(args, fileName);
            view.showMessage("Test file executed: " + fileName);
        } catch (Exception e) {
            view.showError("Failed to execute test file: " + e.getMessage());
        }
    }

    // === Manager Commands handlers ===
    private void handleRegisterCustomer(String[] args) {
        if (args.length < 7) {
            view.showError("Usage:  registerCustomer <firstName> <lastName> <username> <longitude> <latitude> <password>");
            return;
        }
        String firstName = args[1];
        String lastName = args[2];
        String username = args[3];
        double longitude;
        double latitude;
        try {
            longitude = Double.parseDouble(args[4]);
            latitude = Double.parseDouble(args[5]);
        } catch (NumberFormatException e) {
            view.showError("Longitude and latitude must be valid numbers.");
            return;
        }
        String password = args[6];
        try {
            model.addCustomer(firstName, lastName, username, longitude, latitude, password);
            view.showMessage("Customer registered: " + username);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
    private void handleRegisterCourier(String[] args) {
        if (args.length < 7) {
            view.showError("Usage: registerCourier <firstName> <lastName> <username> <longitude> <latitude> <password>");
            return;
        }
        String firstName = args[1];
        String lastName = args[2];
        String username = args[3];
        double longitude;
        double latitude;
        try {
            longitude = Double.parseDouble(args[4]);
            latitude = Double.parseDouble(args[5]);
        } catch (NumberFormatException e) {
            view.showError("Longitude and latitude must be valid numbers.");
            return;
        }
        String password = args[6];
        Coordinate position = new Coordinate(longitude, latitude);
        Courier courier = new Courier(username, password, firstName, lastName, position);
        try {
            model.addCourier(courier);
            view.showMessage("Courier registered: " + username);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
    private void handleRegisterRestaurant(String[] args) {
        if (args.length < 6) {
            view.showError("Usage: registerRestaurant <name> <longitude> <latitude> <username> <password>");
            return;
        }
        String name = args[1];
        String username = args[4];
        double longitude;
        double latitude;
        try {
            longitude = Double.parseDouble(args[2]);
            latitude = Double.parseDouble(args[3]);
        } catch (NumberFormatException e) {
            view.showError("Longitude and latitude must be valid numbers.");
            return;
        }
        String password = args[5];
        Coordinate position = new Coordinate(longitude, latitude);
        try {
            model.addRestaurant(name, position, username, password);
            view.showMessage("Restaurant registered: " + name);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
    private void handleShowMenuItem(String[] args) {
        if (args.length < 2) {
            view.showError("Usage: showMenuItem <restaurantName>");
            return;
        }
        String restaurantName = args[1];
        try {
            model.showMenuItem(restaurantName);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
    public void handleAssociateCard(String[] args) {
        if (args.length < 4) {
            view.showError("Usage: associateCard <userName> <Restaurant> <cardType> ");
            return;
        }
        String userName = args[1];
        String restaurantName = args[2];
        String cardType = args[3];
        try {
            model.associateCard(userName, restaurantName, cardType);
            view.showMessage("Card associated: " + cardType + " for user " + userName);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
    public void handleShowCustomers(String[] args) {
        if (args.length < 1) {
            view.showError("Usage: showCustomers");
            return;
        }
        try {
            model.showCustomers();
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    // === Restaurant management commands ===
    private void handleAddDishRestaurant(String[] args) {
        if (args.length < 5) {
            view.showError("Usage: addDishRestaurantMenu <dishName> <dishCategory> <foodCategory> <unitPrice>");
            return;
        }
        String dishName = args[1];
        String dishCategory = args[2];
        String foodCategory = args[3];
        double unitPrice;
        try {
            unitPrice = Double.parseDouble(args[4]);
        } catch (NumberFormatException e) {
            view.showError("Unit price must be a valid number.");
            return;
        }
        try {
            model.addDish(dishName, dishCategory, foodCategory, unitPrice);
            view.showMessage("Dish added: " + dishName);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
    private void handlCreateMeal(String[] args) {
        if (args.length < 2) {
            view.showError("Usage: createMeal <mealName>");
            return;
        }
        String mealName = args[1];
        try {
            model.createMeal(mealName);
            view.showMessage("Meal created: " + mealName);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
    private void handleAddDish2Meal(String[] args) {
        if (args.length < 3) {
            view.showError("Usage: addDish2Meal <dishName> <mealName>");
            return;
        }
        String dishName = args[1];
        String mealName = args[2];
        try {
            model.addDishToMeal(dishName, mealName);
            view.showMessage("Dish added to meal: " + dishName + " in " + mealName);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
    private void handleShowMeal(String[] args) {
        if (args.length < 2) {
            view.showError("Usage: showMeal <mealName>");
            return;
        }
        String mealName = args[1];
        try {
            view.showMessage("Meal details for: " + mealName);
            model.showMeal(mealName);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
    private void handleSaveMeal(String[] args) {
        if (args.length < 2) {
            view.showError("Usage: saveMeal <mealName>");
            return;
        }
        String mealName = args[1];
        try {
            model.saveMeal(mealName);
            view.showMessage("Meal saved: " + mealName);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
    private void handleSetSpecialOffer(String[] args) {
        if (args.length < 2) {
            view.showError("Usage: setSpecialOffer <mealName>");
            return;
        }
        String mealName = args[1];
        try {
            model.setMealOfTheWeek(mealName);
            view.showMessage("Meal of the week set: " + mealName);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
    private void handleRemoveFromSpecialOffer(String[] args) {
        if (args.length < 2) {
            view.showError("Usage: removeFromSpecialOffer <mealName>");
            return;
        }
        String mealName = args[1];
        try {
            model.removeMealOfTheWeek(mealName);
            view.showMessage("Meal removed from the week: " + mealName);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    // === Customer management commands ===
    public void handleCreateOrder(String[] args) {
        if (args.length < 3) {
            view.showError("Usage: createOrder <restaurantName> <orderName>");
            return;
        }
        String restaurantName = args[1];
        String orderName = args[2];
        try {
            model.createOrder(restaurantName, orderName);
            view.showMessage("Order created: " + orderName + " at " + restaurantName);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    private void handleAddItem2Order(String[] args) {
        if (args.length < 3) {
            view.showError("Usage: addItem2Order <orderName> <itemName>");
            return;
        }
        String orderName = args[1];
        String itemName = args[2];
        try {
            model.addItemToOrder(orderName, itemName);
            view.showMessage("Item added to order: " + itemName + " in " + orderName);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
    private void handleEndOrder(String[] args) {
        if (args.length < 3) {
            view.showError("Usage: endOrder <orderName> <Date> (dd/mm/yyyy hh:mm)");
            return;
        }
        String orderName = args[1];
        String date = args[2] + " " + args[3]; 
        try {
            model.endOrder(orderName, date);
            view.showMessage("Order ended: " + orderName + " on " + date);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    // === Courrier management commands ===
    private void handleOnDutyCourier(String[] args) {
        if (args.length < 2) {
            view.showError("Usage: onDuty <courierName>");
            return;
        }
        String courierName = args[1];
        try {
            model.setCourierOnDuty(courierName);
            view.showMessage("Courier is now on duty: " + courierName);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
    private void handleOffDutyCourier(String[] args) {
        if (args.length < 2) {
            view.showError("Usage: offDuty <courierName>");
            return;
        }
        String courierName = args[1];
        try {
            model.setCourierOffDuty(courierName);
            view.showMessage("Courier is now off duty: " + courierName);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
}