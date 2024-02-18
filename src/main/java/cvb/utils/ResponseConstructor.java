package cvb.utils;

import java.util.ArrayList;

import cvb.exceptions.ConvoBotException;

/**
 * Constructs ConvoBot's response.
 * Includes methods for formatting messages, tasks and errors.
 */
public class ResponseConstructor {

    private final StringBuilder response = new StringBuilder();

    /**
     * Constructs a new {@code ResponseConstructor} instance.
     */
    public ResponseConstructor() {
        // ...
    }

    /**
     * Displays a welcome message in the console.
     */
    public void showWelcomeMsg() {
        response.append("Hello! I'm ConvoBot. What can I do for you?");
    }

    /**
     * Displays an exit message in the console.
     */
    public void showExitMsg() {
        response.append("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the task list in the console.
     *
     * @param tasks the task list to be displayed
     */
    public void showTaskList(TaskList tasks) {
        response.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            try {
                response.append(Integer.toString(index) + "." + tasks.getTaskString(i) + "\n");
            } catch (ConvoBotException e) {
                // impossible to reach here
                assert false;
            }
        }
    }

    /**
     * Displays the matching tasks from the provided ArrayList of tasks.
     * Each task is printed with its corresponding index in the list.
     *
     * @param tasks An ArrayList of Strings containing the task strings to be displayed.
     *
     * @throws NullPointerException If the tasks ArrayList is null.
     */
    public void showMatchingTasks(ArrayList<String> tasks) {
        response.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            response.append(Integer.toString(index) + "." + tasks.get(i) + "\n");
        }
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param taskString the string representation of the added task
     * @param dbSize     the size of the task list after adding the task
     */
    public void showAdded(String taskString, int dbSize) {
        assert dbSize >= 0;
        response.append("Got it. I've added this task:\n");
        response.append("   " + taskString + "\n");
        response.append("Now you have " + Integer.toString(dbSize) + " tasks in the list.\n");
    }

    /**
     * Displays a message indicating that a task has been removed.
     *
     * @param taskString the string representation of the removed task
     * @param dbSize     the size of the task list after removing the task
     */
    public void showRemoved(String taskString, int dbSize) {
        assert dbSize >= 0;
        response.append("Noted. I've removed this task:\n");
        response.append("   " + taskString + "\n");
        response.append("Now you have " + Integer.toString(dbSize) + " tasks in the list.\n");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param taskString the string representation of the marked task
     */
    public void showMarked(String taskString) {
        response.append("Nice! I've marked this task as done:\n");
        response.append("   " + taskString + "\n");
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param taskString the string representation of the unmarked task
     */
    public void showUnmarked(String taskString) {
        response.append("OK, I've marked this task as not done yet:\n");
        response.append("   " + taskString + "\n");
    }

    /**
     * Displays an error message in the console.
     *
     * @param errorMsg the error message to be displayed
     */
    public void showError(String errorMsg) {
        response.append(errorMsg + "\n");
    }

    /**
     * Returns the constructed string and clears the buffer.
     *
     * @return the constructed string.
     */
    public String getResponse() {
        String result = response.toString();
        response.setLength(0);
        return result;
    }
}
