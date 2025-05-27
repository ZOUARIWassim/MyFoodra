package fr.cs.groupJ.myFoodora.main;
import fr.cs.groupJ.myFoodora.controller.sysController;
import fr.cs.groupJ.myFoodora.model.sysModel;
import fr.cs.groupJ.myFoodora.view.sysViewer;
import fr.cs.groupJ.myFoodora.model.user.Customer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Auto {
    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();
        sysModel model = sysModel.getInstance();
        sysViewer view = new sysViewer();
        sysController controller = new sysController(model, view);

        // Path to your commands file
        String commandsFilePath = "commands.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(commandsFilePath))) {
            String commandLine;
            while ((commandLine = br.readLine()) != null) {
                // Process each line from the file as a command
                controller.processCommand(commandLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Optionally, if you want to keep interactive input after file execution:
        /*
        while (true) {
            String commandLine = view.getCommandLine();
            controller.processCommand(commandLine);
        }
        */
    }
}
