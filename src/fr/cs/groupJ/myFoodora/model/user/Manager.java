package fr.cs.groupJ.myFoodora.model.user;

import fr.cs.groupJ.myFoodora.util.Coordinate;
import fr.cs.groupJ.myFoodora.model.restaurant.Restaurant;
import fr.cs.groupJ.myFoodora.factory.FidelityCardFactory;
import fr.cs.groupJ.myFoodora.model.fidelityCard.FidelityCard;
import fr.cs.groupJ.myFoodora.model.user.*;
import fr.cs.groupJ.myFoodora.util.Role;

public class Manager extends User {
    
    private String firstName;
    private String lastName;

    
    public Manager(String username, String password, String firstName, String lastName) {
    	super(username, password, null);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // ===== Getters and Setters =====

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void associateCard(Restaurant restaurant, Customer customer, String cardType) {
        FidelityCardFactory factory = FidelityCardFactory.getInstance();
        FidelityCard card = factory.createFidelityCard(cardType, restaurant, customer);
        customer.addFidelityCard(card);
    }
    
    // public void addUser(User user) {
    //     UserRepository.getInstance().add(user);
    // }

    // /**
    //  * Removes a user by ID.
    //  */
    // public void removeUser(String userId) {
    //     UserRepository.getInstance().remove(userId);
    // }

    // /**
    //  * Activates a user account.
    //  */
    // public void activateUser(String userId) {
    //     UserRepository.getInstance().setActive(userId, true);
    // }

    // /**
    //  * Deactivates a user account.
    //  */
    // public void deactivateUser(String userId) {
    //     UserRepository.getInstance().setActive(userId, false);
    // }

    // /**
    //  * Updates service fee, markup, or delivery cost.
    //  */
    // public void updatePricingPolicy(double serviceFeePct, double markupPct, double deliveryCost) {
    //     PricingPolicy policy = PricingPolicy.getInstance();
    //     policy.setServiceFeePercentage(serviceFeePct);
    //     policy.setMarkupPercentage(markupPct);
    //     policy.setDeliveryCost(deliveryCost);
    // }

    // /**
    //  * Computes total income over a period.
    //  */
    // public double computeTotalIncome(LocalDate from, LocalDate to) {
    //     return OrderRepository.getInstance().sumRevenue(from, to);
    // }

    // /**
    //  * Computes total profit over a period.
    //  */
    // public double computeTotalProfit(LocalDate from, LocalDate to) {
    //     return OrderRepository.getInstance().sumProfit(from, to);
    // }

    // /**
    //  * Computes average income per active customer.
    //  */
    // public double computeAverageIncomePerCustomer(LocalDate from, LocalDate to) {
    //     double income = computeTotalIncome(from, to);
    //     int customers = CustomerRepository.getInstance().countActiveBetween(from, to);
    //     return (customers > 0) ? income / customers : 0;
    // }

    // /**
    //  * Adjusts pricing to meet target profit.
    //  */
    // public void meetTargetProfit(LocalDate from, LocalDate to, double targetProfit) {
    //     // example: simplistic proportion adjustment
    //     double currentProfit = computeTotalProfit(from, to);
    //     double factor = targetProfit / (currentProfit == 0 ? 1 : currentProfit);
    //     PricingPolicy policy = PricingPolicy.getInstance();
    //     policy.setMarkupPercentage(policy.getMarkupPercentage() * factor);
    // }

    // /**
    //  * Finds most or least selling restaurant.
    //  */
    // public Restaurant findTopRestaurant(boolean most) {
    //     return RestaurantRepository.getInstance().findBySales(most);
    // }

    // /**
    //  * Finds most or least active courier.
    //  */
    // public Courier findTopCourier(boolean most) {
    //     return CourierRepository.getInstance().findByDeliveries(most);
    // }

    // /**
    //  * Sets the delivery assignment policy.
    //  */
    // public void setDeliveryPolicy(DeliveryPolicy policy) {
    //     DeliveryAssignmentService.getInstance().setPolicy(policy);
    // }


    // ===== Overridden Methods from User =====

	@Override
	public Role getRole() {
		return Role.MANAGER;
	}

    @Override
    public String toString() {
        return String.format(
            "Manager [id=%d, username=%s, firstName=%s, lastName=%s]",
            getId(), getUsername(), firstName, lastName
        );
    }
}