package duke;

import java.util.ArrayList;

/**
 * Represents the user interface of the application for GUI.
 */
public class Ui {

    /**
     * Returns the welcome message.
     */
    public String showWelcomeMessage() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Returns the goodbye message.
     */
    public String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the task added message.
     *
     * @param task The task that was added.
     * @param taskCount The number of tasks in the list.
     * @return A message indicating the task was added.
     */
    public String showTaskAdded(Task task, int taskCount) {
        return "Got it. I've added this task:\n  " + task
                + "\nNow you have " + taskCount + " tasks in the list.";
    }

    /**
     * Returns the task deleted message.
     *
     * @param task The task that was deleted.
     * @param taskCount The number of tasks in the list.
     * @return A message indicating the task was deleted.
     */
    public String showTaskDeleted(Task task, int taskCount) {
        return "Noted. I've removed this task:\n  " + task
                + "\nNow you have " + taskCount + " tasks in the list.";
    }

    /**
     * Returns the task list.
     *
     * @param tasks The list of tasks.
     * @return A string representation of the task list.
     * @throws DukeException If the task list is empty.
     */
    public String showTaskList(TaskList tasks) throws DukeException {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.getTask(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns the task marked as done message.
     *
     * @param task The task that was marked as done.
     * @return A message indicating the task was marked as done.
     */
    public String showMarkTask(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Returns the task marked as not done message.
     *
     * @param task The task that was marked as not done.
     * @return A message indicating the task was marked as not done.
     */
    public String showUnmarkTask(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    /**
     * Returns the error message.
     *
     * @param message The error message.
     * @return The error message.
     */
    public String showError(String message) {
        return "ERROR: " + message;
    }

    /**
     * Returns the list of tasks found by the search.
     *
     * @param tasks The list of tasks that match the search.
     * @return A string representation of the found tasks.
     */
    public String showFoundTasks(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }
}
