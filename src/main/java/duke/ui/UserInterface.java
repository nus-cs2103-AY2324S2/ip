package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserInterface {
    public static final String INDENT = "     ";

    public static final String LINE = "____________________________________________________________";

    private static final String WELCOME_MESSAGE = String.join(
            "\n",
            "Hello! I'm DEREK",
            INDENT + "What can I do for you?");

    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    private static final Scanner scanner = new Scanner(System.in);

    public static String getUserInput() {
        return scanner.nextLine();
    }

    public static String formatResponse(String msg) {
        return msg;
    }

    public static String formatWelcome() {
        return WELCOME_MESSAGE;
    }

    public static String formatExit() {
        return EXIT_MESSAGE;
    }

    public static String formatTaskAddedResponse(String msg, int numTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Added Task:\n");
        sb.append(INDENT + msg + "\n");
        sb.append("Now you have " + numTasks + " tasks in the list.");
        return sb.toString();
    }

    public static String formatTaskDeletedResponse(String msg, int numTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Deleted Task:\n");
        sb.append("  " + msg + "\n");
        sb.append("Now you have " + numTasks + " tasks in the list.");
        return sb.toString();
    }

    public static String formatTasksByIndicesResponse(ArrayList<String> taskRepresentations) {
        StringBuilder sb = new StringBuilder();
        if (taskRepresentations.size() == 0) {
            sb.append("No matching tasks found.");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (String t : taskRepresentations) {
                sb.append(t + "\n");
            }
        }
        return sb.toString();
    }

    public static String formatErrorResponse(String error) {
        StringBuilder sb = new StringBuilder();
        String[] lines = error.split("\n");
        sb.append("Error: " + lines[0] + "\n");
        for (int i = 1; i < lines.length; i++) {
            sb.append(lines[i]);
        }
        return sb.toString();
    }
}
