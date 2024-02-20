package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the user interface for interacting with the Duke application.
 */
public class Ui {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Reads user input from the console.
     *
     * @return The user input as a string.
     */
    public static String getUserInput() {
        return SCANNER.nextLine().trim();
    }

    /**
     * Displays the welcome message to the user.
     */
    public static String showWelcomeMessage() {
        return "Hello! I'm Georgie. \nWhat can I do for you?";
    }

    /**
     * Displays the goodbye message to the user.
     */
    public static String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public static void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public static void showTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here " + (tasks.size() == 1 ? "is the task" : "are the tasks") + " in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i).getStatusIcon());
            }
        }
    }

    /**
     * Displays a message for an invalid command.
     */
    public static String showInvalidCommand() {
        return "I don't understand what you mean :c";
    }
}
