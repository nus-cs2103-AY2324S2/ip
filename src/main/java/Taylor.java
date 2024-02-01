import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.TaylorException;
import filestorage.Storage;
import parser.Parser;
import tasks.Task;
import ui.Ui;

/**
 * Main class to execute Taylor ChatBot
 */
public class Taylor {
    public static void main(String[] args) {
        List<Task> tasksList = new ArrayList<>();
        // Load pre-existing task from Hard Disk
        try {
            tasksList = Storage.inputFromFile(tasksList);
        } catch (Exception e) {
            Ui.printError(e);
        }
        Ui.welcomeText();
        Scanner type = new Scanner(System.in);

        while (true) {
            try {
                String input = type.nextLine();

                if (input.isBlank()) {
                    Ui.blankCommand();

                } else {
                    Parser.executeCommand(input, tasksList);
                }
            } catch (TaylorException err) {
                break;
            }
        }
        // Save Task into File in Hard Disk
        try {
            Storage.outputToFile(tasksList);
        } catch (Exception e) {
            Ui.printError(e);
        }
        type.close();
        Ui.goodbyeText();
    }
}
