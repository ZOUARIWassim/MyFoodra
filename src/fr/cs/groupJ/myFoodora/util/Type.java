package fr.cs.groupJ.myFoodora.util;

public enum Type {
    PHONE("Phone"),
    EMAIL("Email"),
    ADDRESS("Address");

    private final String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Type{" +
                "type='" + type + '\'' +
                '}';
    }
}