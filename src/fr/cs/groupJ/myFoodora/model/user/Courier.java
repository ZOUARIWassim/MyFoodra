package fr.cs.groupJ.myFoodora.model.user;

import fr.cs.groupJ.myFoodora.util.Coordinate;
import fr.cs.groupJ.myFoodora.util.Role;

public class Courier extends User {
    
    private String firstName;
    private String lastName;
    private boolean isOnDuty;
    private int deliveredOrdersCount;

    public Courier(String username, String password, String firstName, String lastName, Coordinate position) {
        super(username, password, position);
    	this.firstName = firstName;
        this.lastName = lastName;
        this.deliveredOrdersCount = 0;
    }
    // ===== Getters and Setters =====
    public String getFirstName() {
        return firstName;
    }
    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public boolean isOnDuty() {
        return isOnDuty;
    }
    public void setOnDuty() {
        if (isOnDuty) {
            throw new IllegalStateException("Courier is already on duty.");
        }
        isOnDuty = true;
    }
    public void setOffDuty() {
        if (!isOnDuty) {
            throw new IllegalStateException("Courier is already off duty.");
        }
        isOnDuty = false;
    }
    public int getDeliveredOrdersCount() {
        return deliveredOrdersCount;
    }

    /// ===== Methods =====

    public void incrementDeliveredOrdersCount() {
        this.deliveredOrdersCount++;
    }
     
    public void updatePosition(Coordinate newPosition) {
        if (newPosition != null) {
            super.setAdress(newPosition);
        } else {
            throw new IllegalArgumentException("New position cannot be null");
        }
    }

    public boolean refuseDeliveryCall() {
        return false;
    }

    /// ===== Overridden Methods from User =====
    
    @Override
	public Role getRole() {
		return Role.COURIER;
	}

    @Override
    public String toString() {
        return String.format("Courier [id=%d, username=%s, firstName=%s, lastName=%s, position=%s, isOnDuty=%b, deliveredOrdersCount=%d]",
                getId(), getUsername(), firstName, lastName, getAdress(), isOnDuty, deliveredOrdersCount);
    }

}
