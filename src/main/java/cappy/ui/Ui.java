package cappy.ui;

import cappy.task.Task;
import cappy.task.TaskList;

/**
 * User Interface (Ui) interface for interacting with the user and displaying messages.
 *
 * <p>The {@code Ui} interface provides methods for displaying various messages to the user,
 * including newly added tasks, banners, error messages, greetings, and farewell messages.
 */
public interface Ui {
    /**
     * Shows the newly added task and the current number of tasks in the task list to the user.
     *
     * @param task The newly added task.
     * @param tasks The current TaskList.
     */
    public void showAddedTask(Task task, TaskList tasks);

    /**
     * Shows a message to the user.
     *
     * @param message The message to be displayed.
     * @since 1.0
     */
    public void show(String message);

    /**
     * Shows the given messages to the user. Each message will be on a newline.
     *
     * @param messages The String array of messages to be displayed.
     */
    public void show(String[] messages);

    /**
     * Shows an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message);

    /**
     * Shows an exception to the user.
     *
     * @param exception The exception to be displayed.
     */
    public void showError(Exception exception);

    /** Shows a greeting message to the user. */
    public void showGreetings();

    /** Shows a farewell message to the user. */
    public void showExit();
}
