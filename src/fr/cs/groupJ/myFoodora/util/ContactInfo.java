package fr.cs.groupJ.myFoodora.util;
public class ContactInfo {
    public enum Type { EMAIL, PHONE }

    private Type type;
    private String value;

    public ContactInfo(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    // Getters and Setters
}