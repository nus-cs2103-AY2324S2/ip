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
    public static void show(String message) {
        System.out.println("      " + message);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        show("______________________________________________");
        show("Hello! I'm Ben");
        show("What can I do for you?");
        show("______________________________________________");
    }

    /**
     * Displays a line separator.
     */
    public void showLine() {
        show("______________________________________________");
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
    public void showError(String message) {
        show(message);
    }

    /**
     * Displays the exit message to the user.
     */
    public void showExitMessage() {
        show("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the message for listing tasks to the user.
     */
    public void showListMessage() {
        show("Here are the tasks in your list:");
    }

    /**
     * Displays a task to the user.
     *
     * @param task The task to be displayed.
     */
    public void showTask(Task task) {
        show(task.toString());
    }

    /**
     * Displays a specific task from the TaskList to the user.
     *
     * @param tasks The TaskList containing tasks.
     * @param index The index of the task to be displayed.
     */
    public void showTask(TaskList tasks, int index) {
        show(tasks.toString(index));
    }

    /**
     * Displays the entire TaskList to the user.
     *
     * @param tasks The TaskList containing tasks.
     */
    public void showTaskList(TaskList tasks) {
        tasks.showTaskList();
    }

    /**
     * Displays a message for a marked task to the user.
     */
    public void showMarkedTaskMessage() {
        show("Nice! I've marked this task as done:");
    }

    /**
     * Displays a message for an unmarked task to the user.
     */
    public void showUnmarkedTaskMessage() {
        show("OK, I've marked this task as not done yet:");
    }

    /**
     * Displays a message for an added task to the user.
     */
    public void showAddedTaskMessage() {
        show("Got it. I've added this task:");
    }

    /**
     * Displays the current number of tasks in the TaskList to the user.
     *
     * @param tasks The TaskList containing tasks.
     */
    public void showCurrNoOfTasks(TaskList tasks) {
        show("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Displays a message for a deleted task to the user.
     */
    public void showDeletedTaskMessage() {
        show("Noted. I've removed this task:");
    }

    /**
     * Displays a message indicating that matching tasks have been found.
     */
    public void showTasksFoundMessage() {
        show("Here are the matching tasks in your list:");
    }

    /**
     * Displays a message indicating that no matching tasks have been found.
     */
    public void showNoTasksFoundMessage() {
        show("Sorry, there are no matching tasks... Try another keyword!");
    }
}
