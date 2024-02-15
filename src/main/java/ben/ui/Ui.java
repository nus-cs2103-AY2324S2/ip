package ben.ui;

import ben.tasks.Task;
import ben.tasks.TaskList;
import java.util.Scanner;

/**
 * Represents the user interface for the Ben task management application.
 */
public class Ui {
    private final Scanner in;

    /**
     * Constructs a Ui object with a Scanner for user input.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to be displayed.
     */
    public static String show(String message) {
        return "      " + message + "\n";
    }

    /**
     * Displays the welcome message to the user.
     */
    public String showWelcome() {
        return show("______________________________________________") +
                show("Hello! I'm Ben") +
                show("What can I do for you?") +
                show("______________________________________________");
    }

    /**
     * Displays a line separator.
     */
    public String showLine() {
        return show("______________________________________________");
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The user-entered command.
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public String showError(String message) {
        return show(message);
    }

    /**
     * Displays the exit message to the user.
     */
    public String showExitMessage() {
        return show("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the message for listing tasks to the user.
     */
    public String showListMessage() {
        return show("Here are the tasks in your list:");
    }

    /**
     * Displays a task to the user.
     *
     * @param task The task to be displayed.
     */
    public String showTask(Task task) {
        return show(task.toString());
    }

    /**
     * Displays a specific task from the TaskList to the user.
     *
     * @param tasks The TaskList containing tasks.
     * @param index The index of the task to be displayed.
     */
    public String showTask(TaskList tasks, int index) {
        return show(tasks.toString(index));
    }

    /**
     * Displays the entire TaskList to the user.
     *
     * @param tasks The TaskList containing tasks.
     */
    public String showTaskList(TaskList tasks) {
        return tasks.showTaskList();
    }

    /**
     * Displays a message for a marked task to the user.
     */
    public String showMarkedTaskMessage() {
        return show("Nice! I've marked this task as done:");
    }

    /**
     * Displays a message for an unmarked task to the user.
     */
    public String showUnmarkedTaskMessage() {
        return show("OK, I've marked this task as not done yet:");
    }

    /**
     * Displays a message for an added task to the user.
     */
    public String showAddedTaskMessage() {
        return show("Got it. I've added this task:");
    }

    /**
     * Displays the current number of tasks in the TaskList to the user.
     *
     * @param tasks The TaskList containing tasks.
     */
    public String showCurrNoOfTasks(TaskList tasks) {
        return show("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Displays a message for a deleted task to the user.
     */
    public String showDeletedTaskMessage() {
        return show("Noted. I've removed this task:");
    }

    /**
     * Displays a message indicating that matching tasks have been found.
     */
    public String showTasksFoundMessage() {
        return show("Here are the matching tasks in your list:");
    }

    /**
     * Displays a message indicating that no matching tasks have been found.
     */
    public String showNoTasksFoundMessage() {
        return show("Sorry, there are no matching tasks... Try another keyword!");
    }
}
