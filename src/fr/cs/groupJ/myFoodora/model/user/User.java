package fr.cs.groupJ.myFoodora.model.user;

import fr.cs.groupJ.myFoodora.util.ContactInfo;
import fr.cs.groupJ.myFoodora.util.Coordinate;
import fr.cs.groupJ.myFoodora.util.Role;

import java.util.ArrayList;
import java.util.List;

public abstract class User {

    protected static int idCounter = 0; 
    protected int id;
    protected String username;
    protected String password;
    protected Coordinate adress;

    protected List<ContactInfo> contactInfos = new ArrayList<>();
    
    public User( String username, String password, Coordinate adress) {
        this.idCounter++;
        this.id = idCounter;
        this.adress = adress;
        this.username = username;
        this.password = password;
    }

    // ===== Getters and Setters =====

    public int getId() {
        return id;
    }
    protected void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    protected void setUsername(String username) {
        this.username = username;
    }
    protected String getPassword() {
        return password;
    }
    protected void setPassword(String password) {
        this.password = password;
    }
    public Coordinate getAdress() {
        return adress;
    }
    protected void setAdress(Coordinate adress) {
        this.adress = adress;
    }
    public List<ContactInfo> getContactInfos() {
        return contactInfos;
    }
    public void setContactInfos(List<ContactInfo> contactInfos) {
        this.contactInfos = contactInfos;
    }

    // ===== Abstract Methods =====

    public abstract Role getRole();

    // ===== Methods =====

    public boolean checkPassword(String inputPassword) {
        return password.equals(inputPassword);
    }
    public void addContactInfo(ContactInfo info) {
        contactInfos.add(info);
    }

    @Override
    public String toString() {
        return String.format("User [id=%d, username=%s, adress=%s]", id, username, adress);
    }
}
