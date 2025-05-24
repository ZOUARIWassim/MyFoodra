package fr.cs.groupJ.myFoodora.model.fidelityCard;
import fr.cs.groupJ.myFoodora.model.user.Customer;
import fr.cs.groupJ.myFoodora.model.fidelityCard.FidelityCard;

public class PointFidelityCard implements FidelityCard {
    @Override
    public double computeFinalPrice(double basePrice, Customer customer) {
        int points = customer.getPoints();
        if (points >= 100) {
            customer.resetPoints();
            return basePrice * 0.9; // 10% discount
        } else {
            int newPoints = points + (int)(basePrice / 10);
            customer.setPoints(newPoints);
            return basePrice;
        }
    }
}
