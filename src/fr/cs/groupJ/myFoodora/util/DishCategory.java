package fr.cs.groupJ.myFoodora.util;

public enum DishCategory {
    STARTER("Starter"),
    MAIN_COURSE("Main_course"),
    DESSERT("Dessert");

    private final String displayName;

    DishCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static String getAllCategories() {
        StringBuilder categories = new StringBuilder();
        for (DishCategory category : DishCategory.values()) {
            categories.append(category.getDisplayName()).append(", ");
        }
        if (categories.length() > 0) {
            categories.setLength(categories.length() - 2);
        }
        return categories.toString();
    }

    @Override
    public String toString() {
        return displayName;
    }
}