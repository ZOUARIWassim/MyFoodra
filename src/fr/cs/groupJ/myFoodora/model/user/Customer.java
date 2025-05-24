package fr.cs.groupJ.myFoodora.model.user;
import fr.cs.groupJ.myFoodora.model.user.User;
import fr.cs.groupJ.myFoodora.model.fidelityCard.FidelityCard;
import fr.cs.groupJ.myFoodora.model.fidelityCard.*;
import fr.cs.groupJ.myFoodora.util.ContactInfo;
import fr.cs.groupJ.myFoodora.util.Coordinate;
import fr.cs.groupJ.myFoodora.util.Role;

public class Customer extends User {

    private String first_name;
    private String last_name;
    private FidelityCard fidelityCard;
    private int points = 0;
    private boolean specialOfferAgreement = false;
    private ContactInfo preferredContactInfo;

    public Customer( String username, String password,Coordinate adress, String first_name, String last_name) {
        super(username, password,adress);
        this.first_name = first_name;
        this.last_name = last_name;
        this.fidelityCard = new BasicFidelityCard();
    }

    public double placeOrder(double basePrice) {
        return fidelityCard.computeFinalPrice(basePrice, this);
    }

    public int getPoints() {
        return points;
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
    public void setSpecialOfferAgreement(boolean agree) {
        this.specialOfferAgreement = agree;
    }

    public void setPreferredContactInfo(ContactInfo contactInfo) {
        this.preferredContactInfo = contactInfo;
    }
    @Override
    public Role getRole() {
        return Role.CUSTOMER;
    }
}
