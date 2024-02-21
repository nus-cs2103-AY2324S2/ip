package ui;

import java.util.Collections;
import java.util.List;

import exceptions.TaylorException;
import tasks.Task;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private Ui() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * Print out the error message
     * @param err Error thrown from ChatBot
     */
    public static String printError(Exception err) {
        return ("Error: " + err);
    }

    /**
     * Print warning for invalid commands from Users
     */
    public static String invalidCommand() {
        return ("Invalid input. ChatBot can only handle "
                + "'todo', 'deadline', 'event', 'bye', 'list' tasks");
    }

    /**
     * No result found
     * @param response StringBuilder to append
     * @return Appended StringBuilder
     */
    public static StringBuilder emptyResult(StringBuilder response) {
        return response.append("No task found\n");
    }

    /**
     * Print out tasks in the list
     * @param response StringBuilder to append
     * @param result list of tasks queried
     * @return Appended StringBuilder
     */
    public static StringBuilder appendResponse(StringBuilder response, List<Task> result) {
        int index = 1;
        for (Task tsk : result) {
            response.append(index++).append(". ").append(tsk).append("\n");
        }
        return response;
    }

    /**
     * Header reply
     * @param response StringBuilder to append
     * @return Appended StringBuilder
     */
    public static StringBuilder listingStart(StringBuilder response) {
        return response.append("Here are the tasks in your list: \n");
    }

    /**
     * List out the tasks, separated by type of tasks
     * @param taskList lists of tasks lists
     * @param response StringBuilder to append
     * @return Appended StringBuilder
     */
    public static StringBuilder listTasks(List<List<Task>> taskList, StringBuilder response) {
        for (int i = 0; i < taskList.size(); i++) {
            List<Task> currList = taskList.get(i);
            String listType = null;
            switch (i) {
            case 0:
                listType = "Deadline";
                break;
            case 1:
                listType = "Event";
                break;
            case 2:
                listType = "Todo";
                break;
            default:
                break;
            }
            response.append(listType).append(": \n");
            Collections.sort(currList);
            Ui.appendResponse(response, currList);
            response.append("________________ \n");
        }
        return response;
    }

    /**
     * Response output for marking tasks
     * @param response StringBuilder to append
     * @param listToEdit tasks list to be edited
     * @param noToEdit index of the task to be edited
     * @param action mark/unmark
     * @return Appended StringBuilder
     */
    public static StringBuilder markTask(StringBuilder response, List<Task> listToEdit, int noToEdit, String action) {
        if (action.equals("MARK")) {
            listToEdit.get(noToEdit).markIt();
            response.append("Nice! I've marked this task as done:\n");
            response.append(listToEdit.get(noToEdit));
        } else if (action.equals("UNMARK")) {
            listToEdit.get(noToEdit).unMark();
            response.append("OK, I've marked this task as not done yet:\n");
            response.append(listToEdit.get(noToEdit));
        } else {
            throw new TaylorException("Invalid command -  Only use mark/unmark");
        }
        return response;
    }

    /**
     * Response output for deleting tasks
     * @param response StringBuilder to append
     * @param listToEdit tasks list to be edited
     * @param noToEdit index of the task to be edited
     * @return Appended StringBuilder
     */
    public static StringBuilder deleteTask(StringBuilder response, List<Task> listToEdit, int noToEdit) {
        int idx = noToEdit - 1;
        Task taskRemoved = listToEdit.get(idx);
        listToEdit.remove(idx);
        response.append("Noted. I've removed this tasks:\n");
        response.append(taskRemoved).append("\n");
        return response;
    }

    /**
     * Response output for adding tasks
     * @param response StringBuilder to append
     * @param lst tasks list to be edited
     * @param tsk tasks to be inserted into the list
     * @return Appended StringBuilder
     */
    public static StringBuilder addTask(StringBuilder response, List<Task> lst, Task tsk) {
        lst.add(tsk);
        response.append(tsk).append("\n");
        response.append("Got it. I've added this task:\n");
        response.append("Now you have ").append(lst.size()).append(" tasks in the list.").append("\n");
        return response;
    }
}
