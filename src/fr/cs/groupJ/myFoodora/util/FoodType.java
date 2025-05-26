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

    public static String getAllTypes() {
        StringBuilder types = new StringBuilder();
        for (FoodType type : FoodType.values()) {
            types.append(type.getDisplayName()).append(", ");
        }
        if (types.length() > 0) {
            types.setLength(types.length() - 2); // Remove the last comma and space
        }
        return types.toString();
    }

    @Override
    public String toString() {
        return displayName;
    }
}