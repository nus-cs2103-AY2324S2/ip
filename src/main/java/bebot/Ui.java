package bebot;
import java.util.Scanner;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


/**
 * Ui class handles the user interactions of the application
 */

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private Scanner scanner;
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/HuhCatDuke.jpg"));

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the greeting message
     */
    public void showGreeting() {
        printLine();
        showHelp();
        printLine();
    }

    /**
     * Displays a greeting message in the GUI
     */
    public void showGreeting(VBox dialogContainer) {
        Label greetingLabel = new Label("Hello! I'm BEBOT. How can I assist you today?");
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(greetingLabel, new ImageView(duke)));
    }

    /**
     * Prints the exit message
     */
    public void showGoodbye() {
        System.out.println("\t" + "Bye. Hope to see you again soon!");
    }

    /**
     * Displays a goodbye message in the GUI
     */
    public void showGoodbye(VBox dialogContainer) {
        Label goodbyeLabel = new Label("Bye. Hope to see you again soon!");
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(goodbyeLabel, new ImageView(duke)));
    }

    /**
     * Handles user input
     *
     * @return String user input
     */
    public String getUserInput() {
        System.out.println("Enter a task below: ");
        return scanner.nextLine();
    }

    /**
     * Handles user input events in the GUI
     */
    public String getUserInput(TextField userInput) {
        return userInput.getText();
    }

    /**
     * Close the scanner
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Prints the line
     */
    public void printLine() {
        System.out.println(LINE);
    }

    /**
     * Prints list of available commands
     */
    public void showHelp() {
        System.out.println("\t" + "Help: ");

        String[] instructions = {
            "DISPLAY LIST: list",
            "ADD TODO: todo <TASK NAME>",
            "ADD DEADLINE: deadline <TASK NAME> /by <DD/MM/YYYY>",
            "ADD EVENT: event <TASK NAME> /from <DD/MM/YYYY> /to <DD/MM/YYYY>",
            "MARK DONE: mark done <INDEX>",
            "MARK UNDONE: mark undone <INDEX>",
            "DELETE TASK: delete <INDEX>",
            "FIND TASK: find <KEYWORD>",
            "EXIT: bye"
        };
        for (int i = 0; i < instructions.length; i++) {
            System.out.println("\t" + (i + 1) + ". " + instructions[i]);
        }
    }

    /**
     * Invalid task index message
     */
    public void printInvalidTaskIndex() {
        System.out.println("\t" + "Oops, that wasn't a valid task index :P");
    }

    /**
     * Invalid keyword message
     */
    public void printInvalidKeyword() {
        System.out.println("\t" + "No tasks match that keyword :(");
    }
}
