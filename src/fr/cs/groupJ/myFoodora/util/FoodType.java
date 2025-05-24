package fr.cs.groupJ.myFoodora.util;

public enum FoodType {
    VEGETARIAN("Vegetarian"),
    GLUTEN_FREE("Gluten Free"),
    STANDART("Standard");

    private final String displayName;

    FoodType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}