package SamuelBot;

import java.util.List;
import java.util.Scanner;

/**
 * The Ui class handles interactions with the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a new Ui object with a Scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command from the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a welcome message to the user.
     * Provides information on how to add deadline and event tasks.
     */
    public void showWelcome() {
        System.out.println("Hello from SamuelBot!");
        System.out.println("For detailed usages, please refer to the user guide.");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays a confirmation message after adding a task.
     *
     * @param task       The task that was added.
     * @param totalTasks The total number of tasks after adding the new task.
     */
    public void showTaskAddedConfirmation(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon! Have a nice day!");
    }

    public void showMatchingTasks(List<Task> matchingTasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + "." + matchingTasks.get(i));
        }
    }
}
