package fr.cs.groupJ.myFoodora.model.user;
import fr.cs.groupJ.myFoodora.model.user.User;
import fr.cs.groupJ.myFoodora.model.fidelityCard.FidelityCard;
import fr.cs.groupJ.myFoodora.model.order.Order;
import fr.cs.groupJ.myFoodora.model.item.Item;
import fr.cs.groupJ.myFoodora.util.Date;

public class Customer extends User {
    private String name;
    private FidelityCard fidelityCard;
    private int points = 0;
    private Order currentOrder = null;

    public Customer(int id, String username, String password, String name, FidelityCard fidelityCard) {
        super(id, username, password);
        this.name = name;
        this.fidelityCard = fidelityCard;
    }

    public void placeOrder(Item item) {
        Date orderDate = new Date(11, 18, 2022, "12:30");
        this.currentOrder = new Order(this, orderDate);
        currentOrder.addItem(item);
    }

    public int getPoints() {
        return points;
    }

    public FidelityCard getFidelityCard() {
        return fidelityCard;
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

    public Order getCurrentOrder() {
        return currentOrder;
    }
}
