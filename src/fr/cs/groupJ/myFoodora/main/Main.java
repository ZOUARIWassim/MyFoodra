package fr.cs.groupJ.myFoodora.main;

import fr.cs.groupJ.myFoodora.controller.SysController;
import fr.cs.groupJ.myFoodora.model.user.Customer;
import fr.cs.groupJ.myFoodora.model.SysModel;
import fr.cs.groupJ.myFoodora.view.SysViewer;

import java.util.List;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		List<Customer> customers = new ArrayList<>();
		SysModel model = SysModel.getInstance();
        SysViewer view = new SysViewer();
        SysController controller = new SysController(model, view);
        
        while (true) {
            String commandLine = view.getCommandLine();
            controller.processCommand(commandLine);
        }

    }
}
