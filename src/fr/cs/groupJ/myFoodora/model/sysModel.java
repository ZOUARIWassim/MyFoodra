package fr.cs.groupJ.myFoodora.model;
import fr.cs.groupJ.myFoodora.model.user.*;
import fr.cs.groupJ.myFoodora.util.Coordinate;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class sysModel {
	private List<Customer> Customers;
	private List<Courier> Couriers;
	private final Map<String, User> userMap = new HashMap<>();
	private User currentUser;
	
	
	public sysModel(List<Customer> Customers) {
		this.setCustomers(Customers);
        Manager ceo = new Manager("ceo", "123456789", "Default", "Manager");
        userMap.put(ceo.getUsername(), ceo);
		
	}
	
	public List<Customer> getCustomers() {
		return Customers;
	}

	public void setCustomers(List<Customer> customers) {
		Customers = customers;
	}
	
	public void addCustomer(String firstName,String lastName,String username, double longitude,double latitude,String password) {
		if (userMap.containsKey(username)) {
            throw new IllegalArgumentException("Username already exists.");
        }
		Coordinate address = new Coordinate(longitude,latitude);
        Customer customer = new Customer(username, password, address, firstName, lastName);
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

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        this.currentUser = null;
    }
}
