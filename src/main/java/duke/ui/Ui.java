package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * The `Ui` class handles user interaction and provides formatted output.
 * It includes methods for greeting the user, formatting replies, displaying errors,
 * and obtaining user input.
 */
public class Ui {

    /**
     * Horizontal line used for formatting output.
     */
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    /**
     * Scanner object for reading user input.
     */
    private final Scanner scanner;
    /**
     * Constructs an `Ui` object and initializes the Scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    /**
     * Greets the user with an introductory message.
     *
     * @return A formatted greeting message.
     */
    public String greetUser() {
        return "Hello! I'm Zenify\n" + " What can I do for you?";
    }
    /**
     * Formats and prints a reply to the console.
     *
     * @param raw The raw message to be formatted and displayed.
     */
    public void formatReply(String raw) {
        System.out.print(HORIZONTAL_LINE + " " + raw + "\n" + HORIZONTAL_LINE + "\n");
    }
    /**
     * Formats and prints an error message to the console.
     *
     * @param raw The raw error message to be formatted and displayed.
     */
    public void formatError(String raw) {
        System.err.println(HORIZONTAL_LINE + " " + raw + "\n" + HORIZONTAL_LINE);
    }
    /**
     * Displays a farewell message to the user upon exiting the application.
     *
     * @return A formatted farewell message.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }
    /**
     * Generates a formatted string containing the matching tasks and returns the result.
     *
     * @param foundTasks The list of matching tasks.
     * @return A formatted string containing the matching tasks.
     */
    public String showFoundTasks(ArrayList<Task> foundTasks) {
        StringBuilder result = new StringBuilder();
        if (foundTasks.isEmpty()) {
            result.append("No matching tasks found.");
        } else {
            result.append("Here are the matching tasks in your list:");
            int taskCount = 1;
            for (Task task : foundTasks) {
                result.append("\n ").append(taskCount).append(".").append(task);
                taskCount++;
            }
        }
        return result.toString();
    }
    /**
     * Generates a formatted string containing the list of tasks and returns the result.
     *
     * @param tasks The ArrayList of tasks to be displayed.
     * @return A formatted string containing the list of tasks.
     */
    public String showAllTasks(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder();
        if (tasks.isEmpty()) {
            result.append("There are no tasks in the list.");
        } else {
            result.append("Here are the tasks in your list:");
            int taskCount = 1;
            for (Task task : tasks) {
                result.append("\n ").append(taskCount).append(".").append(task);
                taskCount++;
            }
        }
        return result.toString();
    }
    /**
     * Generates a formatted string for marking a task as not done and returns the result.
     *
     * @param task The task to be marked as not done.
     * @return A formatted string confirming the task has been marked as not done.
     */
    public String showUnmarkTaskMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n" + "   " + task;
    }
    /**
     * Generates a formatted string for marking a task as done and returns the result.
     *
     * @param task The task to be marked as done.
     * @return A formatted string confirming the task has been marked as done.
     */
    public String showMarkTaskMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + "   " + task;
    }
    /**
     * Generates a formatted string for adding a task and returns the result.
     *
     * @param task The task that has been added.
     * @param taskCount The current count of tasks in the list.
     * @return A formatted string confirming the addition of the task.
     */
    public String showAddTaskMessage(Task task, int taskCount) {
        return "Got it. I've added this task:\n" + "   " + task + "\n"
                + " Now you have " + taskCount + " task(s) in the list.";
    }
    /**
     * Generates a formatted string for deleting a task and returns the result.
     *
     * @param task The task that has been deleted.
     * @param taskCount The current count of tasks in the list.
     * @return A formatted string confirming the deletion of the task.
     */
    public String showDeleteTaskMessage(Task task, int taskCount) {
        return "Noted. I've removed this task:\n" + "   " + task + "\n"
                + " Now you have " + taskCount + " task(s) in the list.";
    }
    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void displayError(String errorMessage) {
        this.formatError("Error: " + errorMessage);
    }
    /**
     * Retrieves user input from the console.
     *
     * @return The user's input as a String.
     */
    public String getInput() {
        return scanner.nextLine();
    }
    /**
     * Checks if there is more input from the user.
     *
     * @return True if there is more input, false otherwise.
     */
    public boolean hasNext() {
        return scanner.hasNextLine();
    }
    /**
     * Closes the Scanner to release system resources.
     */
    public void closeScanner() {
        scanner.close();
    }
}
