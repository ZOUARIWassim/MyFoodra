package fr.cs.groupJ.myFoodora.model.fidelityCard;
import fr.cs.groupJ.myFoodora.model.user.Customer;
import java.util.Random;

public class LotteryFidelityCard implements FidelityCard {
    private static final double WIN_PROBABILITY = 0.05; // 5% chance

    @Override
    public double computeFinalPrice(double basePrice, Customer customer) {
        Random random = new Random();
        boolean won = random.nextDouble() < WIN_PROBABILITY;
        return won ? 0.0 : basePrice;
    }
}
