package duke;

import java.util.List;
import java.util.Scanner;

import duke.task.Task;

/**
 * The Ui class is responsible for handling user interface interactions and displaying messages to the user.
 * It provides methods for displaying welcome messages, reading user input, showing task-related messages,
 * and handling errors.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs an Ui object and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a line separator to separate different sections of messages.
     */
    public void showLine() {
        System.out.println("_________________________");
    }

    /**
     * Displays a welcome message when the application starts.
     */
    public void showWelcome() {
        System.out.println("\nHello! I'm FICIN!");
        System.out.println("What can I do for you?");
    }

    /**
     * Reads a command from the user.
     *
     * @return The user's input command.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a goodbye message when the user exits the application.
     */
    public void showGoodbye() {
        System.out.println(" \nBye! See you again!");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(" OH NO! " + message);
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task           The task that was added.
     * @param numberOfTasks  The total number of tasks after adding.
     */
    public void showTaskAdded(Task task, int numberOfTasks) {
        System.out.println(" Sure. I have added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + numberOfTasks + " tasks in the list.");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskDone(Task task) {
        System.out.println(" Great! I have marked the task as done:");
        System.out.println("   " + task);
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void showTaskUndone(Task task) {
        System.out.println(" Sure, I have marked this task as not done yet:");
        System.out.println("   " + task);
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task           The task that was deleted.
     * @param numberOfTasks  The total number of tasks after deletion.
     */
    public void showTaskDeleted(Task task, int numberOfTasks) {
        System.out.println(" Got it. I have removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + numberOfTasks + " tasks in the list.");
    }

    /**
     * Displays a list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showTasks(List<Task> tasks) {
        System.out.println(" Here is the list of tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Displays a message to inform the user that no results matching their search criteria were found.
     * This method shows a line separator, a notification message, and another line separator.
     */
    public void showNoTask() {
        System.out.println(" No task found");
    }

    /**
     * Closes the scanner used for user input.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Displays the available commands and their descriptions.
     */
    public void showHelp() {
        System.out.println("Here are the available commands:");
        System.out.println("  - help: show commands");
        System.out.println("  - list: List all tasks");
        System.out.println("  - find <word>: Find tasks");
        System.out.println("  - todo <task>: Add a todo task");
        System.out.println("  - deadline <task> /by <date>: Add a deadline task");
        System.out.println("  - event <task> /from <date> /to <date>: Add an event task");
        System.out.println("    - date can be done by YYYY/MM/dd or YYYY/MM/dd HHmm ");
        System.out.println("    - today & tomorrow can be used for date");
    }
}
