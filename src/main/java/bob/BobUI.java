package bob;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that renders the user interface of the chat bot.
 */
public class BobUI {

    /**
     * List items in list.
     *
     * @param summarized To print a summarized version or detailed version.
     * @param list The bot's task list.
     */
    public String getTaskListText(boolean summarized, ArrayList<Task> list) {

        String listTexts = "";

        if (!summarized) {

            listTexts += "Here are the tasks in your list:\r\n";

            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                listTexts += (i + 1) + "." + task.getType()
                        + task.getStatus() + " " + task + "\r\n";
            }
        } else {
            listTexts = "You have " + list.size()
                    + " tasks in your list.";
        }

        return listTexts;
    }

    /**
     * Prints exception messages
     *
     * @param e The exception object
     */
    public String getErrorText(Exception e) {
        return e.getMessage();
    }

    /**
     * Feedback for when user adds a task.
     *
     * @param t The new task that has been added.
     * @param list The bot's task list.
     */
    public String getTaskAddText(Task t, ArrayList<Task> list) {

        String response = "";
        response += "Here is your newly added task:";
        response += t.getType() + t.getStatus() + " " + t + "\r\n";
        response += getTaskListText(true, list);

        return response;
    }

    /**
     * Prints feedback when a task is deleted.
     *
     * @param t The task that was deleted.
     * @param list Task list.
     */
    public String getTaskDeletionText(Task t, ArrayList<Task> list) {

        String response = "";
        response += "You have removed the current task:";
        response += t.getType() + t.getStatus() + " " + t + "\r\n";
        response += getTaskListText(true, list);

        return response;
    }

    /**
     * Prints feedback when a task is mark as done/undone.
     *
     * @param t The task that was done/undone.
     */
    public String getTaskMarkText(Task t) {
        return t.getType() + t.getStatus() + " " + t;
    }

    public String getTaskDoneText() {
        return "You have marked task as done:";
    }

    public String getTaskUndoneText() {
        return "You have marked task as undone:";
    }

    public String getFindCommandText() {
        return "Here are the matching tasks in your list:";
    }
}
