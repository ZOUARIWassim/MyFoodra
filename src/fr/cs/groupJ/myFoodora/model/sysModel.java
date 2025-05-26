package fr.cs.groupJ.myFoodora.model;

import fr.cs.groupJ.myFoodora.util.Coordinate;
import fr.cs.groupJ.myFoodora.model.user.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class sysModel {

	private List<Customer> Customers = new ArrayList<>();
	private List<Courier> Couriers = new ArrayList<>();
	private final Map<String, User> userMap = new HashMap<>();
	private User currentUser;
	
	public sysModel(List<Customer> Customers) {
		this.Customers = Customers;
        Manager ceo = new Manager("ceo", "123456789", "Default", "Manager");
        userMap.put(ceo.getUsername(), ceo);
		
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

	// ===== Methods =====

	public void addCustomer(String firstName,String lastName,String username, double longitude,double latitude,String password) {
		if (userMap.containsKey(username)) {
            throw new IllegalArgumentException("Username already exists.");
        }
		Coordinate address = new Coordinate(longitude,latitude);
        Customer customer = new Customer(username, password, firstName, lastName, address, new ArrayList<>());
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
