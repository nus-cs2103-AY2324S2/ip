package capone.ui;

import capone.TaskList;
import capone.tasks.Task;

/**
 * The Ui abstract class defines the interface for user interface operations.
 * It includes methods for printing welcome messages and sending messages to the user.
 *
 * @author Tay Rui-Jie
 */
public abstract class Ui {

    /**
     * Prints a welcome message with the Capone logo.
     */
    public void printWelcomeMsg() {
        String logo = "░█▀▀░█▀█░█▀█░█▀█░█▀█░█▀▀░\n"
                + "░█░░░█▀█░█▀▀░█░█░█░█░█▀▀░\n"
                + "░▀▀▀░▀░▀░▀░░░▀▀▀░▀░▀░▀▀▀░";
        System.out.printf("Hello! I'm\n%s\nWhat can I do for you?\n%n", logo);
    }

    /**
     * Sends a goodbye message to the user.
     */
    public abstract void sendGoodbye();

    /**
     * Sends a formatted message after the user creates a Deadline task.
     *
     * @param taskList The list of tasks created.
     */
    public abstract void sendDeadline(TaskList taskList);

    /**
     * Sends a formatted message after the user deletes a task.
     *
     * @param taskList The list of tasks created.
     * @param deletedTask The task that was deleted.
     */
    public abstract void sendDelete(TaskList taskList, Task deletedTask);

    /**
     * Sends a formatted message after the user creates an Event task.
     *
     * @param taskList The list of tasks created.
     */
    public abstract void sendEvent(TaskList taskList);

    /**
     * Sends a formatted message after the user searches for a task
     * has no results.
     *
     * @param keyword The list of tasks created.
     */
    public abstract void sendNoResults(String keyword);

    /**
     * Sends a message displaying the help information.
     */
    public abstract void sendHelp();

    /**
     * Sends a formatted message showing the list of available tasks.
     *
     * @param taskList The list of tasks to display.
     */
    public abstract void sendList(TaskList taskList);

    /**
     * Sends a formatted message after the user marks a task.
     *
     * @param task The task to be marked as complete.
     */
    public abstract void sendMark(Task task);

    /**
     * Sends a formatted message after the user creates a ToDo task.
     *
     * @param taskList The list of tasks created.
     */
    public abstract void sendTodo(TaskList taskList);

    /**
     * Sends a formatted message after the user unmarks a task.
     *
     * @param task The task to be marked as incomplete.
     */
    public abstract void sendUnmark(Task task);
}
