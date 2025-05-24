package fr.cs.groupJ.myFoodora.util;

public enum DishCategory {
    STARTER("Starter"),
    MAIN_COURSE("Main"),
    DESSERT("Dessert");

    private final String displayName;

    DishCategory(String displayName) {
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