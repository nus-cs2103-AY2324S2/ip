package bentley;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * A class representing the user interface of the Bentley task management
 * application.
 */
public class Ui {

    // Scanner object to read user input.
    private Scanner scanner;

    /**
     * Constructs a Ui object with a new Scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads and returns the user input.
     *
     * @return The user input.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Bentley\n" + "What can I do for you?");
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a message confirming the addition of a task.
     *
     * @param task The added task.
     * @param taskCount The total number of tasks after the addition.
     */      
    public void showTaskAddedMessage(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }
    
}
