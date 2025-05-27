package fr.cs.groupJ.myFoodora.factory;

import fr.cs.groupJ.myFoodora.model.fidelityCard.BasicFidelityCard;
import fr.cs.groupJ.myFoodora.model.fidelityCard.FidelityCard;
import fr.cs.groupJ.myFoodora.model.fidelityCard.LotteryFidelityCard;
import fr.cs.groupJ.myFoodora.model.restaurant.Restaurant;
import fr.cs.groupJ.myFoodora.model.fidelityCard.PointFidelityCard;
import fr.cs.groupJ.myFoodora.model.user.Customer;

public class FidelityCardFactory {

    private static FidelityCardFactory instance;

    private FidelityCardFactory() {
        
    }

    public static FidelityCardFactory getInstance() {
        if (instance == null) {
            instance = new FidelityCardFactory();
        }
        return instance;
    }

    public FidelityCard createFidelityCard(String cardType, Restaurant restaurant, Customer customer) {
        switch (cardType.toLowerCase()) {
            case "basic":
                return new BasicFidelityCard(restaurant, customer);
            case "point":
                return new PointFidelityCard(restaurant, customer);
            case "lottery":
                return new LotteryFidelityCard(restaurant, customer);
            default:
                throw new IllegalArgumentException("Unknown fidelity card type: " + cardType);
        }
    }
}