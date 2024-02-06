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
     *
     * @return Return the Capone logo.
     */
    public String printWelcomeMsg() {
        String logo = "░█▀▀░█▀█░█▀█░█▀█░█▀█░█▀▀░\n"
                + "░█░░░█▀█░█▀▀░█░█░█░█░█▀▀░\n"
                + "░▀▀▀░▀░▀░▀░░░▀▀▀░▀░▀░▀▀▀░";
        String output = String.format("Hello! I'm\n%s\nWhat can I do for you?\n%n", logo);
        System.out.printf(output);
        return output;
    }

    /**
     * Sends a goodbye message to the user.
     */
    public abstract String sendGoodbye();

    /**
     * Sends a formatted message after the user creates a Deadline task.
     *
     * @param taskList The list of tasks created.
     */
    public abstract String sendDeadline(TaskList taskList);

    /**
     * Sends a formatted message after the user deletes a task.
     *
     * @param taskList The list of tasks created.
     * @param deletedTask The task that was deleted.
     */
    public abstract String sendDelete(TaskList taskList, Task deletedTask);

    /**
     * Sends a formatted message after the user creates an Event task.
     *
     * @param taskList The list of tasks created.
     */
    public abstract String sendEvent(TaskList taskList);

    /**
     * Sends a formatted message after the user searches for a task
     * has no results.
     *
     * @param keyword The list of tasks created.
     */
    public abstract String sendNoResults(String keyword);

    /**
     * Sends a message displaying the help information.
     */
    public abstract String sendHelp();

    /**
     * Sends a formatted message showing the list of available tasks.
     *
     * @param taskList The list of tasks to display.
     */
    public abstract String sendList(TaskList taskList);

    /**
     * Sends a formatted message after the user marks a task.
     *
     * @param task The task to be marked as complete.
     */
    public abstract String sendMark(Task task);

    /**
     * Sends a formatted message after the user creates a ToDo task.
     *
     * @param taskList The list of tasks created.
     */
    public abstract String sendTodo(TaskList taskList);

    /**
     * Sends a formatted message after the user unmarks a task.
     *
     * @param task The task to be marked as incomplete.
     */
    public abstract String sendUnmark(Task task);
}
