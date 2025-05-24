package fr.cs.groupJ.myFoodora.model.user;
import fr.cs.groupJ.myFoodora.model.user.User;
import fr.cs.groupJ.myFoodora.model.fidelityCard.FidelityCard;

public class Customer extends User {
    private String name;
    private FidelityCard fidelityCard;
    private int points = 0;

    public Customer(int id, String username, String password, String name, FidelityCard fidelityCard) {
        super(id, username, password);
        this.name = name;
        this.fidelityCard = fidelityCard;
    }

    public double placeOrder(double basePrice) {
        return fidelityCard.computeFinalPrice(basePrice, this);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void resetPoints() {
        this.points = 0;
    }

    public void setFidelityCard(FidelityCard fidelityCard) {
        this.fidelityCard = fidelityCard;
    }
}
