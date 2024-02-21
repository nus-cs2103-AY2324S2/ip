package cappy.ui;

import cappy.task.Task;
import cappy.task.TaskList;
import cappy.util.Logger;

import java.util.Scanner;

/**
 * Terminal class for interacting with the user and displaying messages.
 *
 * <p>The {@code Terminal} class provides methods for displaying various messages to the user,
 * including newly added tasks, banners, error messages, greetings, and farewell messages. It
 * utilizes the {@link Logger} class for outputting messages to the console with a consistent
 * format.
 */
public class Terminal implements Ui, AutoCloseable {
    private static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    private static final String INDENT = "    ";
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Reads a line from Standard inupt and return that.
     *
     * @return The line read from stdin.
     */
    public String getInput() {
        return scanner.nextLine();
    }

    /**
     * Shows the newly added task and the current number of tasks in the task list to the user.
     *
     * @param task The newly added task.
     * @param tasks The current TaskList.
     */
    public void showAddedTask(Task task, TaskList tasks) {
        String[] messages = {
            "Got it. I've added this task:",
            task.toString(),
            "Now you have " + tasks.size() + " tasks in the list."
        };
        show(messages);
    }

    /** Shows the Cappy banner to the user. */
    private void showBanner() {
        Logger.print(
                " ██████╗ █████╗ ██████╗ ██████╗ ██╗   ██╗\n"
                        + "██╔════╝██╔══██╗██╔══██╗██╔══██╗╚██╗ ██╔╝\n"
                        + "██║     ███████║██████╔╝██████╔╝ ╚████╔╝ \n"
                        + "██║     ██╔══██║██╔═══╝ ██╔═══╝   ╚██╔╝  \n"
                        + "╚██████╗██║  ██║██║     ██║        ██║   \n"
                        + " ╚═════╝╚═╝  ╚═╝╚═╝     ╚═╝        ╚═╝   \n");
    }

    /**
     * Shows a message to the user.
     *
     * @param message The message to be displayed.
     * @since 1.0
     */
    public void show(String message) {
        Logger.print(INDENT + HORIZONTAL_LINE);
        Logger.print(INDENT + " " + message);
        Logger.print(INDENT + HORIZONTAL_LINE + "\n");
    }

    /**
     * Shows the given messages to the user. Each message will be on a newline.
     *
     * @param messages The String array of messages to be displayed.
     */
    public void show(String[] messages) {
        Logger.print(INDENT + HORIZONTAL_LINE);
        for (String msg : messages) {
            Logger.print(INDENT + " " + msg);
        }
        Logger.print(INDENT + HORIZONTAL_LINE + "\n");
    }

    /**
     * Shows an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        Logger.error(message);
    }

    /**
     * Shows an exception to the user.
     *
     * @param exception The exception to be displayed.
     */
    public void showError(Exception exception) {
        Logger.error(exception.getMessage());
    }

    /** Shows a greeting message to the user. */
    public void showGreetings() {
        showBanner();
        String[] messages = {"Hello! I'm Cappy", "What can I do for you?"};
        show(messages);
    }

    /** Shows a farewell message to the user. */
    public void showExit() {
        show("Bye. Hope to see you again soon!");
    }

    @Override
    public void close() {
        scanner.close();
    }
}
