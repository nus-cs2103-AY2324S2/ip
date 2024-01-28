package duke;

import java.util.ArrayList;

/**
 * Represents the user interface of the application.
 */
public class Ui {

    /**
     * Prints the welcome message.
     */
    public void showWelcomeMessage() {
        printSeparator();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printSeparator();
    }

    /**
     * Prints the goodbye message.
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
    }

    /**
     * Prints the task added message.
     *
     * @param task The task that was added.
     * @param taskCount The number of tasks in the list.
     */
    public void showTaskAdded(Task task, int taskCount) {
        printSeparator();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printSeparator();
    }

    /**
     * Prints the task deleted message.
     *
     * @param task The task that was deleted.
     * @param taskCount The number of tasks in the list.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        printSeparator();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printSeparator();
    }

    /**
     * Prints the task list.
     *
     * @param tasks The list of tasks.
     * @throws DukeException If the task list is empty.
     */
    public void showTaskList(TaskList tasks) throws DukeException {
        printSeparator();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
        printSeparator();
    }

    /**
     * Prints the task marked as done message.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkTask(Task task) {
        printSeparator();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        printSeparator();
    }

    /**
     * Prints the task marked as not done message.
     *
     * @param task The task that was marked as not done.
     */
    public void showUnmarkTask(Task task) {
        printSeparator();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        printSeparator();
    }

    /**
     * Prints the error message.
     *
     * @param message The error message.
     */
    public void showError(String message) {
        printSeparator();
        System.out.println("ERROR: " + message);
        printSeparator();
    }

    /**
     * Displays the list of tasks found by the search.
     *
     * @param tasks The list of tasks that match the search.
     */
    public void showFoundTasks(ArrayList<Task> tasks) {
        printSeparator();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        printSeparator();
    }

    /**
     * Prints the separator.
     */
    public void printSeparator() {
        System.out.println("____________________________________________________________");
    }
}
