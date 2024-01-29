/*
 * Ui.java
 * This class handles the user interface for the Duke application, including input and output.
 * It provides methods for reading user commands and displaying messages.
 */

package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Ui {
    private final BufferedReader reader;
    private final PrintWriter writer;

    /**
     * Creates a new Ui instance with BufferedReader for input and PrintWriter for output.
     */
    public Ui() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out, true);
    }

    /**
     * Reads a user command from the input.
     *
     * @return The user command entered by the user.
     * @throws IOException If there is an issue reading the input.
     */
    public String readCommand() throws IOException {
        return reader.readLine();
    }

    /**
     * Displays a horizontal line as a separator.
     */
    public void showLine() {
        writer.println("____________________________________________________________");
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        writer.println(message);
    }

    /**
     * Displays an error message indicating a problem with loading tasks from a file.
     */
    public void showLoadingError() {
        showMessage("Error loading tasks from file.");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbyeMessage() {
        showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Displays an error message to the user.
     *
     * @param error The error message to display.
     */
    public void showError(String error) {
        showMessage(error);
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param tasks        The TaskList containing tasks.
     * @param removedTask  The Task object that has been removed.
     */
    public void showDeletedMessage(TaskList tasks, Task removedTask) {
        String taskWord = tasks.size() == 1 ? " task" : " tasks";
        writer.println("Noted. I've removed this task:\n  " + removedTask);
        writer.println("Now you have " + tasks.size() + taskWord + " in the list.");
    }

    /**
     * Closes the PrintWriter used for output.
     */
    public void close() {
        writer.close();
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        showMessage("Hello! I'm Nicky!");
        showMessage("What can I do for you?");
        showLine();
    }

    /**
     * Displays a message indicating a task has been added.
     *
     * @param tasks The TaskList containing tasks.
     */
    public void showAddedTask(TaskList tasks) {
        String taskWord = tasks.size() == 1 ? " task" : " tasks";
        writer.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
        writer.println("Now you have " + tasks.size() + taskWord + " in the list.");
    }

    /**
     * Retrieves the PrintWriter for writing output.
     *
     * @return The PrintWriter for writing output.
     */
    public PrintWriter getWriter() {
        return this.writer;
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The Task object that has been marked as done.
     */
    public void showMarkedMessage(Task task) {
        if(task.isDone()) {
            writer.println("This task is already marked as done:\n  " + task);
        } else {
            task.markAsDone();
            writer.println("Nice! I've marked this task as done:\n  " + task);
        }
    }

    /**
     * Displays a message indicating a task has been marked as not done.
     *
     * @param task The Task object that has been marked as not done.
     */
    public void showUnmarkedMessage(Task task) {
        if(task.isDone()) {
            task.markAsNotDone();
            writer.println("OK, I've marked this task as not done yet:\n  " + task);
        } else {
            writer.println("This task is already marked as not done:\n  " + task);
        }
    }
}
