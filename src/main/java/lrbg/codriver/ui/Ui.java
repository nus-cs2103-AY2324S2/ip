package lrbg.codriver.ui;

import lrbg.codriver.data.Task;
import lrbg.codriver.data.TaskList;
import lrbg.codriver.data.exception.CoDriverException;

import java.util.Scanner;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    /** The scanner to read input from the user. */
    private final Scanner scanner;

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next command from the user.
     *
     * @return The next command from the user.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Closes the UI.
     */
    public void close() {
        this.scanner.close();
    }

    /**
     * Shows the greeting message.
     */
    public void showGreeting() {
        showLine();
        System.out.println("Hello! I'm CoDriver, your everyday AI companion!");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Shows the goodbye message.
     */
    public void showGoodbye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Shows the message for adding a task.
     *
     * @param task The task to be added.
     * @param size The size of the task list.
     */
    public void showAddTask(Task task, int size) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size  + " tasks in the list.");
        showLine();
    }

    /**
     * Shows the message for marking a task as done.
     *
     * @param task The task to be marked as done.
     */
    public void showMarkTask(Task task) {
        showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        showLine();
    }

    /**
     * Shows the message for marking a task as not done.
     *
     * @param task The task to be marked as not done.
     */
    public void showUnmarkTask(Task task) {
        showLine();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(task);
        showLine();
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public void showList(TaskList tasks) {
        showLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int listIndex = i + 1;
            System.out.println(listIndex + ". " + tasks.get(i));
        }
        showLine();
    }

    /**
     * Shows the message for deleting a task.
     *
     * @param task The task to be deleted.
     * @param size The size of the task list.
     */
    public void showDeleteTask(Task task, int size) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }

    /**
     * Shows the tasks in the task list that match a keyword.
     * 
     * @param tasks The list of tasks that match the keyword.
     */
    public void showMatchingTasks(TaskList tasks) {
        showLine();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int listIndex = i + 1;
            System.out.println(listIndex + ". " + tasks.get(i));
        }
        showLine();
    }

    /**
     * Shows the given error.
     *
     * @param e The error to be shown.
     */
    public void showError(CoDriverException e) {
        showLine();
        System.out.println(e.getMessage());
        showLine();
    }

    /**
     * Shows a message for a number format error.
     */
    public void showNumberFormatError() {
        showLine();
        System.out.println("Error! Argument provided must be a number!");
        showLine();
    }

    /**
     * Shows a message for a file loading error.
     */
    public void showFileLoadingError() {
        showLine();
        System.out.println("Error! Unable to load file!");
        showLine();
    }

    /**
     * Shows a line to separate lines in the terminal.
     */
    public void showLine() {
        System.out.println("------------------------------------------------");
    }
}
