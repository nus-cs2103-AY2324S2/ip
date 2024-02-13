package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the user interface for the Duke application.
 * This class handles all user interactions.
 *
 * @author Qin Boan
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next command from the user input.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm BBJSOBB");
        System.out.println("What can I do for you?");
    }

    /**
     * Shows an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Shows a loading error message.
     */
    public void showLoadingError() {
        showError("Could not load tasks from file.");
    }

    /**
     * Displays a task to the user.
     *
     * @param task The task to be displayed.
     */
    public void showTask(String task) {
        System.out.println(task);
    }

    /**
     * Shows a message indicating that a task has been added.
     *
     * @param task The task that has been added.
     * @param taskCount The total number of tasks after adding.
     */
    public void showAddedTask(String task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Shows a message indicating that a task has been removed.
     *
     * @param task The task that has been removed.
     * @param taskCount The total number of tasks after removal.
     */
    public void showRemovedTask(String task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param tasks The TaskList containing tasks to be displayed.
     */
    public void showTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
    }

    /**
     * Shows the exit message to the user.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Closes the scanner when the UI is closing.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Displays a list of tasks to the user. If the list is empty, a message indicating
     * no matching tasks is shown. Otherwise, all matching tasks are displayed.
     *
     * @param tasks The ArrayList of tasks to be displayed.
     */
    public void showMatchingTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks in your list.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }
}
