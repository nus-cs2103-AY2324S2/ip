package ui;

import java.util.Collections;
import java.util.List;

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

    public static StringBuilder emptyResult(StringBuilder response) {
        return response.append("No task found\n");
    }

    public static StringBuilder appendResponse(StringBuilder response, List<Task> result) {
        int index = 1;
        for (Task tsk : result) {
            response.append(index++).append(". ").append(tsk).append("\n");
        }
        return response;
    }

    public static StringBuilder listingStart(StringBuilder response) {
        return response.append("Here are the tasks in your list: \n");
    }

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
}
