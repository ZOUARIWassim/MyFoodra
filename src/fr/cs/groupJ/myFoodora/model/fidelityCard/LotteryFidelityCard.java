package fr.cs.groupJ.myFoodora.model.fidelityCard;

import fr.cs.groupJ.myFoodora.model.fidelityCard.FidelityCard;
import fr.cs.groupJ.myFoodora.model.restaurant.Restaurant;
import fr.cs.groupJ.myFoodora.model.user.Customer;

import java.util.Random;

public class LotteryFidelityCard extends FidelityCard {

    private static final double WIN_PROBABILITY = 0.5; 

    public LotteryFidelityCard(Restaurant restaurant, Customer customer) {
        super(restaurant, customer);
    }

    @Override
    public double computeFinalPrice(double basePrice) {
        Random random = new Random();
        boolean won = random.nextDouble() < WIN_PROBABILITY;
        return won ? 0.0 : basePrice;
    }
}
