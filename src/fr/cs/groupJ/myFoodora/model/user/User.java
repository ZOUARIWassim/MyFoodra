package fr.cs.groupJ.myFoodora.model.user;
import fr.cs.groupJ.myFoodora.util.Role;
import fr.cs.groupJ.myFoodora.util.ContactInfo;
import java.util.ArrayList;
import java.util.List;
import fr.cs.groupJ.myFoodora.util.Coordinate;
public abstract class User {
    protected String username;
    protected String password;
    protected Coordinate adress;
    protected List<ContactInfo> contactInfos = new ArrayList<>();
    
    public User( String username, String password,Coordinate adress) {
        this.adress = adress;
        this.username = username;
        this.password = password;
    }
    public boolean checkPassword(String inputPassword) {
        return password.equals(inputPassword);
    }
    // ===== Getters and Setters =====

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Coordinate getAdress() {
        return adress;
    }
    
    public void setAdress(Coordinate adress) {
        this.adress = adress;
    }

    // ===== Abstract Methods =====
    
    public void addContactInfo(ContactInfo info) {
        contactInfos.add(info);
    }

    public abstract Role getRole();

}
