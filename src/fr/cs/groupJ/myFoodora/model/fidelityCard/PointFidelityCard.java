package fr.cs.groupJ.myFoodora.model.fidelityCard;

import fr.cs.groupJ.myFoodora.model.fidelityCard.FidelityCard;
import fr.cs.groupJ.myFoodora.model.restaurant.Restaurant;
import fr.cs.groupJ.myFoodora.model.user.Customer;

public class PointFidelityCard extends FidelityCard {

    public PointFidelityCard(Restaurant restaurant, Customer customer) {
        super(restaurant, customer);
    }

    @Override
    public double computeFinalPrice(double basePrice) {
        Customer customer = getCustomer();
        int points = customer.getPoints();
        if (points >= 100) {
            customer.resetPoints();
            return basePrice * 0.9; 
        } else {
            int newPoints = points + (int)(basePrice / 10);
            customer.setPoints(newPoints);
            return basePrice;
        }
    }
}
