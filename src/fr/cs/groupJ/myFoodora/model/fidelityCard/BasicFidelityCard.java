package fr.cs.groupJ.myFoodora.model.fidelityCard;
import fr.cs.groupJ.myFoodora.model.user.Customer;
import fr.cs.groupJ.myFoodora.model.fidelityCard.FidelityCard;

public class BasicFidelityCard implements FidelityCard {
    @Override
    public double computeFinalPrice(double basePrice, Customer customer) {
        // Access to special offers logic
        return basePrice; // For now, no discount applied
    }
}
