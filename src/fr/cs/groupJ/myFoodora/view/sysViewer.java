package fr.cs.groupJ.myFoodora.view;

import fr.cs.groupJ.myFoodora.model.SysModel;

import java.util.Scanner;

public class SysViewer {

	private final Scanner scanner = new Scanner(System.in);
	
	public String getCommandLine() {
        System.out.print("> ");
        return scanner.nextLine();
    }
    public void showMessage(String message) {
        System.out.println(message);
    }
    public void showError(String message) {
        System.err.println("Error: " + message);
    }
    public void showHelp() {
        System.out.println("Available commands:");
        System.out.println("  help - Show this help message");
        System.out.println("  exit - Exit the application");
    }
}
