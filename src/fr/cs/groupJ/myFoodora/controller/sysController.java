package fr.cs.groupJ.myFoodora.controller;
import fr.cs.groupJ.myFoodora.model.sysModel;
import fr.cs.groupJ.myFoodora.view.*;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import fr.cs.groupJ.myFoodora.model.user.*;
import fr.cs.groupJ.myFoodora.util.AccessControl;
import fr.cs.groupJ.myFoodora.util.Coordinate;
import fr.cs.groupJ.myFoodora.util.Role;


public class sysController {
	private sysModel model;
    private sysViewer view;

    public sysController(sysModel model, sysViewer view) {
        this.model = model;
        this.view = view;
    }
    

    public void processCommand(String commandLine) {
        String[] tokens = parseArgs(commandLine);
        if (tokens.length == 0) return;

        String command = tokens[0];

        try {
            switch (command) {
                case "registerCustomer":
                	if (AccessControl.roleControl(Role.MANAGER, model)) {
                    handleRegisterCustomer(tokens);}else {view.showError("Access denied. This command requires role: " + Role.MANAGER);}
                	
                    break;
                case "login":
                    handleLogin(tokens);
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    view.showError("Unknown command: " + command);
            }
        } catch (Exception e) {
            view.showError("Command failed: " + e.getMessage());
        }
    }

    private String[] parseArgs(String input) {
        
        List<String> args = new ArrayList<>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(input);
        while (m.find()) {
            args.add(m.group(1).replace("\"", ""));
        }
        return args.toArray(new String[0]);
    }

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
        model.addCustomer(firstName,lastName,username,longitude, latitude,password);
        view.showMessage("Customer registered: " + username);
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
    
    private void handleRegisterCourier(String[] args) {
        	

        if (args.length < 6) {
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
}
