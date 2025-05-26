package fr.cs.groupJ.myFoodora.main;
import fr.cs.groupJ.myFoodora.controller.sysController;
import fr.cs.groupJ.myFoodora.model.sysModel;
import fr.cs.groupJ.myFoodora.view.sysViewer;
import fr.cs.groupJ.myFoodora.model.user.Customer;
import java.util.List;
import java.util.ArrayList;
public class Main {
	public static void main(String[] args) {
		List<Customer> customers = new ArrayList<>();
		sysModel model = sysModel.getInstance();
        sysViewer view = new sysViewer();
        sysController controller = new sysController(model, view);
        
        while (true) {
            String commandLine = view.getCommandLine();
            controller.processCommand(commandLine);
        }

    }
}
