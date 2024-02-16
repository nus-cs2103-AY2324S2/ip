package nicky;

import nicky.task.Task;
import nicky.task.TaskList;

/**
 * Handles the user interface for the Nicky application, including input and output.
 */
public class Ui {

    /**
     * Returns a goodbye message to the user.
     *
     * @return A string containing the goodbye message.
     */
    public String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns an error message.
     *
     * @param error The error message to be displayed.
     * @return A string containing the specified error message.
     */
    public String showError(String error) {
        return error;
    }

    /**
     * Returns a message indicating that a task has been deleted.
     *
     * @param tasks       The TaskList containing tasks.
     * @param removedTask The Task object that has been removed.
     * @return A string detailing the deletion of the task and the current number of tasks remaining.
     */
    public String showDeletedMessage(TaskList tasks, Task removedTask) {
        String taskWord = tasks.size() == 1 ? " task" : " tasks";
        return "Noted. I've removed this task:\n  " + removedTask
            + "\nNow you have " + tasks.size() + taskWord + " in the list.";
    }

    /**
     * Returns a welcome message to the user.
     *
     * @return A string containing the welcome message.
     */
    public String showWelcome() {
        return "Hello! I'm Nicky!" + "\nWhat can I do for you?";
    }

    /**
     * Returns a message indicating that a task has been added.
     *
     * @param tasks The TaskList containing tasks.
     * @return A string detailing the addition of the task and the current total number of tasks.
     */
    public String showAddedTask(TaskList tasks) {
        String taskWord = tasks.size() == 1 ? " task" : " tasks";
        return "Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1)
            + "\nNow you have " + tasks.size() + taskWord + " in the list.";
    }

    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task The Task object that has been marked as done.
     * @return A string indicating that the specified task has been marked as done,
     *      or that it was already marked as done.
     */
    public String showMarkedMessage(Task task) {
        if (task.isDone()) {
            return "This task is already marked as done:\n  " + task;
        } else {
            task.markAsDone();
            return "Nice! I've marked this task as done:\n  " + task;
        }
    }

    /**
     * Returns a message indicating that a task has been marked as not done.
     *
     * @param task The Task object that has been marked as not done.
     * @return A string indicating that the specified task has been marked as not done,
     *      or that it was already marked as not done.
     */
    public String showUnmarkedMessage(Task task) {
        if (task.isDone()) {
            task.markAsNotDone();
            return "OK, I've marked this task as not done yet:\n  " + task;
        } else {
            return "This task is already marked as not done:\n  " + task;
        }
    }
}
