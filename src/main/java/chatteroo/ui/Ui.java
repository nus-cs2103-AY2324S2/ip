package chatteroo.ui;

import chatteroo.ChatterooException;
import chatteroo.tasks.Task;

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
        return "G'day mate, ChatterOO here!\n" + "What can I do for ya?";
    }

    /**
     * Shows the exit response.
     */
    public String showByeResponse() {
        return "Cheers mate! ChatterOO will catch ya later!\n";
    }

    /**
     * Shows response for adding a task.
     * @param newTask The task that was added.
     * @param listCount The number of tasks in the list.
     * @return The response for adding a task.
     */
    public String showAddTaskResponse(Task newTask, int listCount) {
        String message = "UnderstOOd! I've added this task:\n" + newTask.toString() + "\n";
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
        String message = "OOOK! I've removed this task:\n" + deletedTask.toString() + "\n";
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
            return "Crikey mate, here are no matching tasks in your list.\n" + "Do yOO want to try again?\n";
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
            return "There are nOO tasks in the list. GOO add some!\n";
        }
        return "Here are the tasks in your list:\n";
    }

    /**
     * Shows the response when the task is marked as done.
     * @return The response when the task is marked as done.
     */
    public String showMarkDoneTaskResponse() {
        return "BravOO mate! I've marked this task as done:\n";
    }

    /**
     * Shows the response when the task is marked as not done.
     * @return The response when the task is marked as not done.
     */
    public String showMarkNotDoneTaskResponse() {
        return "OK mate, I've marked this task as not done yet:\n";
    }

    /**
     * Shows the response when the task list is cleared of tasks marked as done.
     * @return The response when the task list is cleared of done tasks.
     */
    public String showClearDoneTaskResponse() {
        return "Good on ya! I've removed all the tasks that are done from the list.\n";
    }

    /**
     * Shows the response when an error occurs.
     * @param e The error that occurred.
     * @return The response when an error occurs.
     */
    public String showErrorResponse(ChatterooException e) {
        return e.getMessage();
    }

    public String showInvalidTaskNumberResponse() {
        return "Mate, ya can't give a invalid task number, y'know?\n";
    }

}
