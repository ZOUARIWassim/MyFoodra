package fr.cs.groupJ.myFoodora.model.user;
import fr.cs.groupJ.myFoodora.util.Coordinate;
import fr.cs.groupJ.myFoodora.util.Role;

public class Courier extends User {
    
    private String firstName;
    private String lastName;
    private String username;
    private Coordinate position;
    private boolean isOnDuty;
    private int deliveredOrdersCount;

    
    public Courier(String username, String password, String firstName, String lastName, Coordinate position) {
        super(username, password, position);
    	this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.position = position;
        this.deliveredOrdersCount = 0;
        this.isOnDuty = true;  
    }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public Coordinate getPosition() { return position; }
    public int getDeliveredOrdersCount() { return deliveredOrdersCount; }
    public boolean getDuty() {return isOnDuty;}
  

    public void updatePosition(double x, double y) {
        this.position.setLatitude(x);
        this.position.setLongitude(y);
    }

    public void setOnDuty(boolean onDuty) {
        isOnDuty = onDuty;
    }
   

    public boolean refuseDeliveryCall() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("Courier[id=%s, name=%s %s, state=%s, position=%s, delivered=%d]", 
                 firstName, lastName, position, deliveredOrdersCount);
    }
	@Override
	public Role getRole() {
		return Role.COURIER;
	}
}
