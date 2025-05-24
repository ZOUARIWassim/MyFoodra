package fr.cs.groupJ.myFoodora.model.fidelityCard;
import fr.cs.groupJ.myFoodora.model.user.Customer;

public interface FidelityCard {
    double computeFinalPrice(double basePrice, Customer customer);
}
