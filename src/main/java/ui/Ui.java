package ui;

import java.util.Collections;
import java.util.List;

import exceptions.TaylorException;
import tasks.Task;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private Ui() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * Prints out the error message.
     *
     * @param err Error thrown from ChatBot
     */
    public static StringBuilder printError(Exception err) {
        StringBuilder response = new StringBuilder();
        response.append("Error: ").append(err);
        return response;
    }

    /**
     * Prints warning for invalid commands from Users
     */
    public static StringBuilder invalidCommand() {
        StringBuilder response = new StringBuilder();
        response.append("I'm lost in the whispers of the wind.\n")
                .append("Trying to decode the words you send.\n")
                .append("Tangled up in the branches of doubt,\n")
                .append("I'm left wondering what it's all about. \n")
                .append("==============================\n")
                .append("(Taylor can only handle:\n")
                .append("'todo', 'deadline', 'event', 'delete', 'find', 'mark', 'search' tasks)");
        return response;
    }

    /**
     * Edits responses to show 'No result found'.
     *
     * @param response StringBuilder to append
     * @return Appended StringBuilder
     */
    public static StringBuilder emptyResult(StringBuilder response) {
        return response.append("In the quiet of the morning dew,\n")
                .append("Silent whispers in the rain.\n")
                .append("==============================\n")
                .append("No task found\n");
    }

    /**
     * Prints out tasks in the list.
     *
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
     * Edits response for 'Header' reply.
     *
     * @param response StringBuilder to append
     * @return Appended StringBuilder
     */
    public static StringBuilder listingStart(StringBuilder response) {
        return response.append("The memories we shared, the dream we wove,\n")
                .append("Now scattered like petals, lost in the grove.\n");
    }

    /**
     * Lists out the tasks, separated by type of tasks.
     *
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
     * Edits response output for marking tasks.
     *
     * @param response StringBuilder to append
     * @param listToEdit tasks list to be edited
     * @param noToEdit index of the task to be edited
     * @param action mark/unmark
     * @return Appended StringBuilder
     */
    public static StringBuilder markTask(StringBuilder response, List<Task> listToEdit, int noToEdit, String action) {
        if (action.equals("MARK")) {
            listToEdit.get(noToEdit).markIt();
            response.append("In the constellation of dreams, I've made my mark:\n");
            response.append(listToEdit.get(noToEdit));
        } else if (action.equals("UNMARK")) {
            listToEdit.get(noToEdit).unMark();
            response.append("In the pages of my mind, I trace.\n")
                    .append("The paths where memories once embraced.\n")
                    .append("But now they're scattered\n")
                    .append("Unmarked trails, lost in the dark\n");
            response.append(listToEdit.get(noToEdit));
        } else {
            throw new TaylorException("In the silence of the midnight air, \n "
                    + "I search for words, but they're not here.\n"
                    + "==============================\n"
                    + "Please only use mark/unmark");
        }
        return response;
    }

    /**
     * Edits response output for deleting tasks.
     *
     * @param response StringBuilder to append
     * @param listToEdit tasks list to be edited
     * @param noToEdit index of the task to be edited
     * @return Appended StringBuilder
     */
    public static StringBuilder deleteTask(StringBuilder response, List<Task> listToEdit, int noToEdit) {
        int idx = noToEdit - 1;
        Task taskRemoved = listToEdit.get(idx);
        listToEdit.remove(idx);
        response.append("With every stroke, a memory fades:\n");
        response.append(taskRemoved).append("\n");
        return response;
    }

    /**
     * Edits response output for adding tasks.
     *
     * @param response StringBuilder to append
     * @param lst tasks list to be edited
     * @param tsk tasks to be inserted into the list
     * @return Appended StringBuilder
     */
    public static StringBuilder addTask(StringBuilder response, List<Task> lst, Task tsk) {
        lst.add(tsk);
        response.append("With gentle hands, I've added you,\n").append("To the story of skies so blue.\n");
        response.append(tsk).append("\n");
        response.append("Now you have ").append(lst.size()).append(" tasks in the list.").append("\n");
        return response;
    }
}
