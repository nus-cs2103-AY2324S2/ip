package seedu.chatteroo.ui;

import seedu.chatteroo.ChatterooException;
import seedu.chatteroo.tasks.Task;

/**
 * Represents the user interface of the Chatteroo ChatBot program.
 */
public class Ui {
    /**
     * Constructor for the Ui class.
     */
    public Ui() {
    }

    /**
     * Shows the welcome response.
     */
    public static String showWelcomeResponse() {
        return "Hello! I'm Chatteroo!\n" + "What can I do for you?";
    }

    /**
     * Shows the exit response.
     */
    public String showByeResponse() {
        return "Chatteroo says bye and hopes to see you again soon!\n";
    }

    /**
     * Shows response for adding a task.
     * @param newTask The task that was added.
     * @param listCount The number of tasks in the list.
     * @return The response for adding a task.
     */
    public String showAddTaskResponse(Task newTask, int listCount) {
        String message = "Got it. I've added this task:\n" + newTask.toString() + "\n";
        message += ("Now you have " + listCount + " tasks in the list.\n");
        return message;
    }

    /**
     * Shows response for deleting a task.
     * @param deletedTask The task that was deleted.
     * @param listCount The number of tasks in the list.
     * @return The response for deleting a task.
     */
    public String showDeleteTaskResponse(Task deletedTask, int listCount) {
        String message = "I've removed this task:\n" + deletedTask.toString() + "\n";
        message += ("Now you have " + listCount + " tasks in the list.\n");
        return message;
    }

    /**
     * Shows response based on whether specified task is found in the list
     * @param listCount The number of tasks in the list.
     * @return The specified response to be shown.
     */
    public String showFindTaskResponse(int listCount) {
        if (listCount == 0) {
            return "There are no matching tasks in your list.\n";
        }
        return "Here are the matching tasks in your list:\n";
    }

    /**
     * Shows specified response based on task availability in the list
     * @param listCount The number of tasks in the list.
     * @return The specified response to be shown.
     */
    public String showListTaskResponse(int listCount) {
        if (listCount == 0) {
            return "There are no tasks in the list.\n";
        }
        return "Here are the tasks in your list:\n";
    }

    /**
     * Shows the response when the task is marked as done.
     * @return The response when the task is marked as done.
     */
    public String showMarkDoneTaskResponse() {
        return "Nice! I've marked this task as done:\n";
    }

    /**
     * Shows the response when the task is marked as not done.
     * @return The response when the task is marked as not done.
     */
    public String showMarkNotDoneTaskResponse() {
        return "OK, I've marked this task as not done yet:\n";
    }

    public String showClearDoneTaskResponse() {
        return "I've removed all the tasks that are done from the list.\n";
    }

    public String showErrorResponse(ChatterooException e) {
        return e.getMessage();
    }

}
