package fr.cs.groupJ.myFoodora.model.user;
import fr.cs.groupJ.myFoodora.util.Coordinate;

/**
 * Represents a courier in the myFoodora system.
 */
public class Courier {
    /** Unique identifier for the courier */
    private final String id;
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private Coordinate position;
    private State state;
    private int deliveredOrdersCount;

    /**
     * Possible states of a courier.
     */
    public enum State {
        ON_DUTY,
        OFF_DUTY
    }

    /**
     * Constructs a new Courier with the given initial data.
     * 
     * @param id               unique courier identifier
     * @param firstName        courier's first name
     * @param lastName         courier's last name
     * @param username         login username
     * @param phoneNumber      contact phone number
     * @param initialPosition  starting position instance
     */
    public Courier(String id, String firstName, String lastName, String username, String phoneNumber, Coordinate initialPosition) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.position = initialPosition;
        this.state = State.OFF_DUTY;
        this.deliveredOrdersCount = 0;
    }


    public String getId() { return id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public Coordinate getPosition() { return position; }
    public State getState() { return state; }
    public int getDeliveredOrdersCount() { return deliveredOrdersCount; }

    public void goOnDuty() {
        this.state = State.ON_DUTY;
    }

    public void goOffDuty() {
        this.state = State.OFF_DUTY;
    }


    public void updatePosition(double x, double y) {
        this.position.setLatitude(x);
        this.position.setLongitude(y);
    }


    public boolean acceptDeliveryCall() {
        if (this.state == State.ON_DUTY) {
            this.deliveredOrdersCount++;
            return true;
        }
        return false;
    }


    public boolean refuseDeliveryCall() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("Courier[id=%s, name=%s %s, state=%s, position=%s, delivered=%d]", 
                id, firstName, lastName, state, position, deliveredOrdersCount);
    }
}
