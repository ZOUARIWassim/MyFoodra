package fr.cs.groupJ.myFoodora.controller;

import fr.cs.groupJ.myFoodora.util.AccessControl;
import fr.cs.groupJ.myFoodora.util.Coordinate;
import fr.cs.groupJ.myFoodora.model.sysModel;
import fr.cs.groupJ.myFoodora.model.user.*;
import fr.cs.groupJ.myFoodora.model.restaurant.Restaurant;
import fr.cs.groupJ.myFoodora.util.Role;
import fr.cs.groupJ.myFoodora.view.*;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class sysController {

	private sysModel model;
    private sysViewer view;

    public sysController(sysModel model, sysViewer view) {
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
        if (tokens.length == 0) return;

        String command = tokens[0];

        try {
            switch (command) {
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
                case "registerDishRestaurant":
                    if (AccessControl.roleControl(Role.RESTAURANT, model)) {
                        handleAddDishRestaurant(tokens);
                    } else {
                        view.showError("Access denied. This command requires role: " + Role.RESTAURANT);
                    }

                default:
                    view.showError("Unknown command: " + command);
            }
        } catch (Exception e) {
            view.showError("Command failed: " + e.getMessage());
        }
    }

    // === Command handlers ===

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
        String password = args[5];
        try {
            model.addCustomer(firstName, lastName, username, longitude, latitude, password);
            view.showMessage("Customer registered: " + username);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
    
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
        String password = args[5];
        Coordinate position = new Coordinate(longitude, latitude);
        Courier courier = new Courier(username, password, firstName, lastName, position);
        try {
            model.addCourier(courier);
            view.showMessage("Courier registered: " + username);
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    // === Restaurant management commands ===

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
    private void handleAddDishRestaurant(String[] args) {
        if (args.length < 5) {
            view.showError("Usage: addDishRestaurant <dishName> <dishCategory> <foodCategory> <unitPrice>");
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
    // private void handlCreateMeal(String[] args) {
    //     if (args.length < 2) {
    //         view.showError("Usage: createMeal <mealName>");
    //         return;
    //     }
    //     String mealName = args[1];
    //     try {
    //         model.createMeal(mealName);
    //         view.showMessage("Meal created: " + mealName);
    //     } catch (IllegalArgumentException e) {
    //         view.showError(e.getMessage());
    //     }
    // }
    // public handleAddDish2Meal(String[] args) {
    //     if (args.length < 3) {
    //         view.showError("Usage: addDish2Meal <dishName> <mealName>");
    //         return;
    //     }
    //     String dishName = args[1];
    //     String mealName = args[2];
    //     User currentUser = model.getCurrentUser();
    //     if (currentUser == null || !(currentUser instanceof Restaurant)) {
    //         view.showError("You must be logged in as a restaurant to add a dish to a meal.");
    //         return;
    //     }
    //     Restaurant restaurant = (Restaurant) currentUser;
    //     try {
    //         model.addDishToMeal(dishName, mealName);
    //         view.showMessage("Dish added to meal: " + dishName + " in " + mealName);
    //     } catch (IllegalArgumentException e) {
    //         view.showError(e.getMessage());
    //     }
    // }
    // public handleShowMeal(String[] args) {
    //     if (args.length < 2) {
    //         view.showError("Usage: showMeal <mealName>");
    //         return;
    //     }
    //     String mealName = args[1];
    //     User currentUser = model.getCurrentUser();
    //     if (currentUser == null || !(currentUser instanceof Restaurant)) {
    //         view.showError("You must be logged in as a restaurant to show a meal.");
    //         return;
    //     }
    //     Restaurant restaurant = (Restaurant) currentUser;
    //     try {
    //         model.showMeal(mealName);
    //         view.showMealDetails(meal);
    //     } catch (IllegalArgumentException e) {
    //         view.showError(e.getMessage());
    //     }
    // }
    // public handleSaveMeal(String[] args) {
    //     if (args.length < 2) {
    //         view.showError("Usage: saveMeal <mealName>");
    //         return;
    //     }
    //     String mealName = args[1];
    //     User currentUser = model.getCurrentUser();
    //     if (currentUser == null || !(currentUser instanceof Restaurant)) {
    //         view.showError("You must be logged in as a restaurant to save a meal.");
    //         return;
    //     }
    //     Restaurant restaurant = (Restaurant) currentUser;
    //     try {
    //         model.saveMeal(mealName);
    //         view.showMessage("Meal saved: " + mealName);
    //     } catch (IllegalArgumentException e) {
    //         view.showError(e.getMessage());
    //     }
    // }
    // public handleSetSpecialOffer(String[] args) {
    //     if (args.length < 2) {
    //         view.showError("Usage: setSpecialOffer <mealName>");
    //         return;
    //     }
    //     String mealName = args[1];
    //     User currentUser = model.getCurrentUser();
    //     if (currentUser == null || !(currentUser instanceof Restaurant)) {
    //         view.showError("You must be logged in as a restaurant to set a meal of the week.");
    //         return;
    //     }
    //     Restaurant restaurant = (Restaurant) currentUser;
    //     try {
    //         model.setMealOfTheWeek(mealName);
    //         view.showMessage("Meal of the week set: " + mealName);
    //     } catch (IllegalArgumentException e) {
    //         view.showError(e.getMessage());
    //     }
    // }
    // public handleRemoveFromSpecialOffer(String[] args) {
    //     if (args.length < 2) {
    //         view.showError("Usage: removeFromSpecialOffer <mealName>");
    //         return;
    //     }
    //     String mealName = args[1];
    //     User currentUser = model.getCurrentUser();
    //     if (currentUser == null || !(currentUser instanceof Restaurant)) {
    //         view.showError("You must be logged in as a restaurant to remove a meal from the week.");
    //         return;
    //     }
    //     Restaurant restaurant = (Restaurant) currentUser;
    //     try {
    //         model.removeMealOfTheWeek(mealName);
    //         view.showMessage("Meal removed from the week: " + mealName);
    //     } catch (IllegalArgumentException e) {
    //         view.showError(e.getMessage());
    //     }
    // }

}
