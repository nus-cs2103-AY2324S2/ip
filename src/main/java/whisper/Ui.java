package whisper;

import java.util.ArrayList;

/**
 * The Ui class handles the user interface of the Whisper application, including input/output and displaying messages.
 */
public class Ui {
    public static String NAME = "Whisper";
    private StringBuilder responseBuilder;

    public Ui() {
        responseBuilder = new StringBuilder();
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcomeMsg() {
        responseBuilder.append("Hello! I'm ").append(NAME).append(", your personal chat bot!\n");
        responseBuilder.append("What can I do for you?\n");
    }

    /**
     * Prints an error message to the console.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void printError(String errorMessage) {
        responseBuilder.append("\nError: ").append(errorMessage);
    }

    /**
     * Reads a user command from the console.
     *
     * @return The user-entered command.
     */
    public String inputCommand() {
        return "Enter your input: ";
    }

    /**
     * Prints an error message for failed file loading.
     */
    public void printLoadFileError() {
        printError("Error loading the file.");
    }

    /**
     * Displays the list of tasks to the console.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void printTasks(ArrayList<Task> tasks) {
        responseBuilder.append("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            responseBuilder.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
    }

    /**
     * Prints a message confirming the addition of a task.
     *
     * @param task       The task that was added.
     * @param totalTask The total number of tasks after the addition.
     */
    public void printTaskAdded(Task task, int totalTask) {
        responseBuilder.append("Got it. I've added this task:\n").append(task)
                .append("\nNow you have ").append(totalTask).append(" tasks in the list.\n");
    }

    /**
     * Prints a message confirming the marking of a task as done.
     *
     * @param task The task that was marked as done.
     */
    public void printTaskAsDone(Task task) {
        responseBuilder.append("Nice! I've marked this task as done:\n").append(task);
    }

    /**
     * Prints a message confirming the marking of a task as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void printTaskAsUndone(Task task) {
        responseBuilder.append("Nice! I've marked this task as not done:\n").append(task);
    }

    /**
     * Prints a message confirming the removal of a task.
     *
     * @param task       The task that was removed.
     * @param totalTasks The total number of tasks after the removal.
     */
    public void printRemovedTask(Task task, int totalTasks) {
        responseBuilder.append("Noted! I've removed this task:\n").append(task)
                .append("\nNow you have ").append(totalTasks).append(" tasks in the list.\n");
    }

    /**
     * Prints tasks that match a given keyword.
     *
     * @param matchingTasks The list of tasks that match the keyword.
     */
    public void printMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            responseBuilder.append("No matching task found.\n");
        } else {
            responseBuilder.append("Here are the matching tasks in your list!\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                responseBuilder.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
            }
        }
    }

    public String getResponse() {
        String response = responseBuilder.toString();
        responseBuilder = new StringBuilder(); // Reset response builder for the next command
        return response;
    }

    /**
     * Displays the exit message when the application exits.
     */
    public void printExitMessage() {
        responseBuilder.append("Bye. Hope to see you again soon!");
    }
}