package duke.response;

import duke.task.TaskList;

import java.util.Scanner;

/**
 * Handles response to user input in the application.
 */
public class Response {
    private static final String TAB = "  ";

    /**
     * Constructs a Response object.
     */
    public Response () { }

    public String getMessage(String message) {
        return String.format("     %s\n", message);
    }

    /**
     * Returns the message for adding a task.
     *
     * @param newTask String representation of added task.
     * @return Message of adding a task.
     */
    public String getAddTaskMessage(String newTask) {
        assert !newTask.isEmpty() : "Empty Task";
        return getMessage("Got it. I've added this task:")
                + getMessage(TAB + newTask);
    }

    /**
     * Returns the message for marking a task.
     *
     * @param task String representation of marked task.
     * @return Message of marking a task.
     */
    public String getMarkMessage(String task) {
        assert !task.isEmpty() : "Empty Task";
        return getMessage("Nice! I've marked this task as done:")
                + getMessage(TAB + task);
    }

    /**
     * Returns the message for unmarking a task.
     *
     * @param task String representation of unmarked task.
     * @return Message of unmarking a task.
     */
    public String getUnmarkMessage(String task) {
        assert !task.isEmpty() : "Empty Task";
        return getMessage("OK, I've marked this task as not done yet:")
                + getMessage(TAB + task);
    }

    /**
     * Generates a message for deleting a task.
     *
     * @param task String representation of deleted task.
     * @param size The updated size of the task list after deletion.
     * @return A formatted message displaying the deleted task and updated task list size.
     */
    public String getDeleteMessage(String task, int size) {
        assert !task.isEmpty() : "Empty Task";
        assert size >= 0 : "Invalid size";
        return getMessage("Got it. I've removed this task:")
                + getMessage(TAB + task)
                + getMessage("Now you have " + size + " tasks in the list!");
    }

    /**
     * Generates a message for displaying all tasks in the task list.
     *
     * @param tasks The task list containing all tasks.
     * @return A formatted message displaying all tasks in the task list.
     */
    public String getListMessage(TaskList tasks) {
        return getMessage("Here are the tasks in your list:")
                + tasks.toString();
    }

    /**
     * Generates a message for finding tasks matching a keyword.
     *
     * @param tasks String representation of the tasks matching the given keyword.
     * @return A formatted message displaying the tasks found matching the keyword.
     */
    public String getFindMessage(String tasks) {
        return getMessage("Here are the tasks in your list:")
                + tasks;
    }

    /**
     * Returns a string of goodbye message.
     *
     * @return Message for user exiting the application.
     */
    public String getGoodbye() {
        return getMessage("Bye. Hope to see you again soon!");
    }

    public String getTagMessage(String taskWithTag) {
        assert !taskWithTag.isEmpty() : "Empty Task";
        return getMessage("OK, I've tagged this task:")
                + getMessage(TAB + taskWithTag);
    }
}
