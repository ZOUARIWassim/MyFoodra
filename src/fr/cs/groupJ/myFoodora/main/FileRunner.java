package fr.cs.groupJ.myFoodora.main;

import fr.cs.groupJ.myFoodora.controller.SysController;
import fr.cs.groupJ.myFoodora.model.user.Customer;
import fr.cs.groupJ.myFoodora.model.SysModel;
import fr.cs.groupJ.myFoodora.view.SysViewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.List;

public class FileRunner {
    public static void main(String[] args, String filePath) {
        List<Customer> customers = new ArrayList<>();
        SysModel model = SysModel.getInstance();
        SysViewer view = new SysViewer();
        SysController controller = new SysController(model, view);

        String commandsFilePath = filePath;

        try (BufferedReader br = new BufferedReader(new FileReader(commandsFilePath))) {
            String commandLine;
            while ((commandLine = br.readLine()) != null) {
                controller.processCommand(commandLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
