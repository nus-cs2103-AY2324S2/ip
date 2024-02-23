package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the user interface for the Duke application.
 * This class handles all user interactions by returning strings to be printed.
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
     * Returns the welcome message.
     *
     * @return The welcome message string.
     */
    public String showWelcome() {
        return "Vanthanemmmmmmmmm! I'm BernardBot\nWhat can I do for you?";
    }

    /**
     * Returns an error message.
     *
     * @param message The error message to be returned.
     * @return The error message string.
     */
    public String showError(String message) {
        return "Error: " + message;
    }

    /**
     * Returns the loading error message.
     *
     * @return The loading error message string.
     */
    public String showLoadingError() {
        return showError("Could not load tasks from file.");
    }

    /**
     * Returns the string representation of a task.
     *
     * @param task The task to be displayed.
     * @return The task string.
     */
    public String showTask(String task) {
        return task;
    }

    /**
     * Returns a message indicating that a task has been added.
     *
     * @param task The task that has been added.
     * @param taskCount The total number of tasks after adding.
     * @return The task added message string.
     */
    public String showAddedTask(String task, int taskCount) {
        return "Got it. I've added this task:\n" + task + "\nNow you have " + taskCount + " tasks in the list.";
    }

    /**
     * Returns a message indicating that a task has been removed.
     *
     * @param task The task that has been removed.
     * @param taskCount The total number of tasks after removal.
     * @return The task removed message string.
     */
    public String showRemovedTask(String task, int taskCount) {
        return "Noted. I've removed this task:\n" + task + "\nNow you have " + taskCount + " tasks in the list.";
    }

    /**
     * Returns a message indicating that the user input is invalid
     *
     * @return The invalid message
     */
    public String showInvalidTask() {
        return "That's not a valid task!\n";
    }

    /**
     * Returns a message indicating that the user input for Todo is invalid
     *
     * @return The invalid message
     */
    public String showInvalidTodo() {
        return "That's not a valid task!\n";
    }

    /**
     * Returns a message indicating that the user input for Deadline is invalid
     *
     * @return The invalid message
     */
    public String showInvalidDeadline() {
        return "That's not a valid task!\n";
    }

    /**
     * Returns a message indicating that the user input for Event is invalid
     *
     * @return The invalid message
     */
    public String showInvalidEvent() {
        return "That's not a valid task!\n";
    }

    /**
     * Returns a string representation of all tasks in the task list.
     *
     * @param tasks The TaskList containing tasks to be displayed.
     * @return The task list string.
     */
    public String showTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.getTask(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns the exit message.
     *
     * @return The exit message string.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Closes the scanner when the UI is closing.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Returns a string representation of a list of matching tasks.
     *
     * @param tasks The ArrayList of tasks that match the search criteria.
     * @return The matching tasks list string.
     */
    public String showMatchingTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No matching tasks in your list.";
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
            return sb.toString();
        }
    }
}
