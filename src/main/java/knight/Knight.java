package knight;

import java.util.Scanner;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * Represents the main class of the program.
 */
public class Knight {
    private static TaskList tasks = new TaskList();

    public Knight() {
        tasks = Storage.readFromFile();
    }

    /**
     * Starts the command line interface for the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        tasks = Storage.readFromFile();
        Ui.speak("Greetings, Your Excellency! Thy humble knight"
                + "doth stand before thee. How may I serve thee on this day?");

        Scanner scan = new Scanner(System.in);
        String message;

        while (true) {
            message = scan.nextLine();
            Command command;

            try {
                command = CommandParser.parseCommand(message);
            } catch (NonstandardCommandException e) {
                Ui.speak(e.getMessage());
                continue;
            }

            if (command == Command.BYE) {
                break;
            } else {
                tasks.executeCommand(command, message);
            }
        }

        scan.close();
        Storage.writeToFile(tasks);
        Ui.speak("Farewell, Your Excellency! May we cross paths once more in the near future.");
    }

    /**
     * Returns the response of the knight to the user input.
     *
     * @param input The user input.
     * @return The response of the knight to the user input.
     */
    private static String knightExecute(String message) {
        Command command;

        try {
            command = CommandParser.parseCommand(message);
        } catch (NonstandardCommandException e) {
            return e.getMessage();
        }

        if (command == Command.BYE) {
            Storage.writeToFile(tasks);
            return "Farewell, Your Excellency! May we cross paths once more in the near future.";
        } else {
            return tasks.executeCommandReturnString(command, message);
        }
    }

    /**
     * Returns the response of the knight to the user input.
     *
     * @param input The user input.
     * @return The response of the knight to the user input.
     */
    String getResponse(String input) {
        return knightExecute(input);
    }
}
