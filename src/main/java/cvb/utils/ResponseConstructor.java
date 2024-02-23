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
     * Adds a welcome message to the constructed response.
     */
    public void addWelcomeMsg() {
        response.append("Hello! I'm ConvoBot. What can I do for you?");
    }

    /**
     * Adds an exit message to the constructed response.
     */
    public void addExitMsg() {
        response.append("Bye. Hope to see you again soon!");
    }

    /**
     * Adds the task list to the constructed response.
     *
     * @param tasks The task list to be added.
     */
    public void addTaskList(TaskList tasks) {
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
     * Adds the matching tasks from the provided ArrayList of tasks to the constructed response.
     * Each task is added with its corresponding index in the list.
     *
     * @param tasks An ArrayList of Strings containing the task strings to be added.
     * @throws NullPointerException If the tasks ArrayList is null.
     */
    public void addMatchingTasks(ArrayList<String> tasks) {
        response.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            response.append(Integer.toString(index) + "." + tasks.get(i) + "\n");
        }
    }

    /**
     * Adds a message indicating that a task has been added.
     *
     * @param taskString The string representation of the added task.
     * @param dbSize     The size of the task list after adding the task.
     */
    public void addAdded(String taskString, int dbSize) {
        assert dbSize >= 0;
        response.append("Got it. I've added this task:\n");
        response.append("   " + taskString + "\n");
        response.append("Now you have " + Integer.toString(dbSize) + " tasks in the list.\n");
    }

    /**
     * Adds a message indicating that a task has been removed.
     *
     * @param taskString The string representation of the removed task.
     * @param dbSize     The size of the task list after removing the task.
     */
    public void addRemoved(String taskString, int dbSize) {
        assert dbSize >= 0;
        response.append("Noted. I've removed this task:\n");
        response.append("   " + taskString + "\n");
        response.append("Now you have " + Integer.toString(dbSize) + " tasks in the list.\n");
    }

    /**
     * Adds a message indicating that a task has been marked as done.
     *
     * @param taskString The string representation of the marked task.
     */
    public void addMarked(String taskString) {
        response.append("Nice! I've marked this task as done:\n");
        response.append("   " + taskString + "\n");
    }

    /**
     * Adds a message indicating that a task has been marked as not done.
     *
     * @param taskString The string representation of the unmarked task.
     */
    public void addUnmarked(String taskString) {
        response.append("OK, I've marked this task as not done yet:\n");
        response.append("   " + taskString + "\n");
    }

    /**
     * Adds an error message.
     *
     * @param errorMsg The error message.
     */
    public void addError(String errorMsg) {
        response.append(errorMsg + "\n");
    }

    /**
     * Returns the constructed string and clears the buffer.
     *
     * @return The constructed string.
     */
    public String getResponse() {
        String result = response.toString();
        response.setLength(0);
        return result;
    }
}
