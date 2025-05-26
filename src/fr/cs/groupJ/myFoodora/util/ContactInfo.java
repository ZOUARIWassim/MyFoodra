package fr.cs.groupJ.myFoodora.util;

public class ContactInfo {

    private Type type;
    private String value;

    public ContactInfo(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    // ====== Getters and Setters =====
    
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}