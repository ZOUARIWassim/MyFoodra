package fr.cs.groupJ.myFoodora.view;
import fr.cs.groupJ.myFoodora.model.sysModel;
import java.util.Scanner;


public class sysViewer {
	private final Scanner scanner = new Scanner(System.in);
	//private sysModel sys;

	
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
	/**
	@Override
	public void update(Observable o, Object arg) {
	this.showMessage("Hello");
	}*/

}
