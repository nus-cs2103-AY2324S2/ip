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
     * Shows the message for adding a task.
     *
     * @param task The task to be added.
     * @param size The size of the task list.
     * @return The message for adding a task.
     */
    public String showAddTask(Task task, int size) {
        return "Got it. I've added this task:\n" +
                task + "\n" +
                "Now you have " + size + " tasks in the list.\n";
    }

    /**
     * Shows the message for marking a task as done.
     *
     * @param task The task to be marked as done.
     * @return The message for marking a task as done.
     */
    public String showMarkTask(Task task) {
        return "Nice! I've marked this task as done:\n" +
                task + "\n";
    }

    /**
     * Shows the message for marking a task as not done.
     *
     * @param task The task to be marked as not done.
     *
     * @return The message for marking a task as not done.
     */
    public String showUnmarkTask(Task task) {
        return "Ok, I've marked this task as not done yet:\n" +
                task + "\n";
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks The list of tasks.
     *
     * @return The list of tasks.
     */
    public String showList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            int listIndex = i + 1;
            sb.append(listIndex).append(". ").append(tasks.getTask(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Shows the message for deleting a task.
     *
     * @param task The task to be deleted.
     * @param size The size of the task list.
     *
     * @return The message for deleting a task.
     */
    public String showDeleteTask(Task task, int size) {
        return "Noted. I've removed this task:\n" +
                task + "\n" +
                "Now you have " + size + " tasks in the list.\n";
    }

    /**
     * Shows the tasks in the task list that match a keyword.
     * 
     * @param tasks The list of tasks that match the keyword.
     *
     * @return The tasks in the task list that match a keyword.
     */
    public String showMatchingTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            int listIndex = i + 1;
            sb.append(listIndex).append(". ").append(tasks.getTask(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Shows the given error.
     *
     * @param e The error to be shown.
     *
     * @return The given error.
     */
    public String showError(CoDriverException e) {
        return e.getMessage();
    }

    /**
     * Shows a message for a number format error.
     *
     * @return The message for a number format error.
     */
    public String showNumberFormatError() {
        return "Error! Please enter a valid number!";
    }

    /**
     * Shows a message for a file loading error.
     *
     * @return The message for a file loading error.
     */
    public String showFileLoadingError() {
        return "Error! Unable to load file!";
    }

    /**
     * Shows a line to separate lines in the terminal.
     *
     * @return The line to separate lines in the terminal.
     */
    public String showLine() {
        return "------------------------------------------------\n";
    }
}
