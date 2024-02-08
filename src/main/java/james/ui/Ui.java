package james.ui;

import java.util.List;
import java.util.Scanner;

import james.exception.DukeException;
import james.tasklist.TaskList;
import james.tasks.Task;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructor for Ui class.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The next line of input from the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm james.James!\nWhat can I do for you?\n");
    }

    /**
     * Shows the goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    /**
     * Shows the error message to the user.
     *
     * @param message The error message to be shown.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Shows the task to the user.
     *
     * @param message The message to be shown.
     */
    public void showTask(String message, Task task, int size) {
        System.out.println(message + "\n" + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Shows the task list to the user.
     *
     * @param tasks The task list to be shown.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            try {
                System.out.println((i + 1) + ". " + tasks.getTask(i));
            } catch (DukeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Shows the tasks found to the user.
     *
     * @param tasks The tasks found to be shown.
     */
    public void showTasksFound(List<String> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks match your search keyword.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Shows the tasks found to the user.
     *
     * @param task The tasks found to be shown.
     * @param remainingTasks The number of remaining tasks.
     */
    public void showDeletedTask(Task task, int remainingTasks) {
        System.out.println("Noted. I've removed this task: " + task);
        System.out.println("Now you have " + remainingTasks + " tasks in the list.");
    }

    /**
     * Shows the tasks found to the user.
     *
     * @param task The tasks found to be shown.
     */
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Shows the tasks found to the user.
     *
     * @param task The tasks found to be shown.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }
}
