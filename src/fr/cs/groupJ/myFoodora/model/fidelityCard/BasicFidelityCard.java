package fr.cs.groupJ.myFoodora.model.fidelityCard;

import fr.cs.groupJ.myFoodora.model.fidelityCard.FidelityCard;
import fr.cs.groupJ.myFoodora.model.restaurant.Restaurant;
import fr.cs.groupJ.myFoodora.model.user.Customer;

public class BasicFidelityCard extends FidelityCard {

    public BasicFidelityCard(Restaurant restaurant, Customer customer) {
        super(restaurant, customer);
        customer.subscribeRestaurant(restaurant);
    }

    @Override
    public double computeFinalPrice(double basePrice) {
        return basePrice; 
    }
}
