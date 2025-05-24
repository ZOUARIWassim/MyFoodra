package fr.cs.groupJ.myFoodora.model.user;

public abstract class User {
    protected int id;
    protected String username;
    protected String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // ===== Getters and Setters =====

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    // ===== Abstract Methods =====


    // Optional login/logout methods
    public void login() {
        // Implement login logic or leave for Controller
        System.out.println(username + " logged in.");
    }

    public void logout() {
        // Implement logout logic
        System.out.println(username + " logged out.");
    }
}
