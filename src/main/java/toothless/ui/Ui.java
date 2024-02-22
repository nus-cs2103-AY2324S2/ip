package toothless.ui;

import java.util.ArrayList;

import toothless.task.Task;

/**
 * Class for dealing with interactions with the user.
 */
public class Ui {
    /**
     * Returns new task message.
     *
     * @param newTask New Task to be printed.
     * @param taskListSize Size of tasklist.
     * @return String message of new task.
     */
    public String getNewTaskMessage(Task newTask, int taskListSize) {
        return String.format("Got it. I've added this task:\n\t%s\nNya-ow you have %d tasks in the list.",
                newTask, taskListSize);
    }

    /**
     * Returns deleted task message.
     *
     * @param deletedTask Deleted Task to be printed.
     * @param taskListSize Size of tasklist.
     * @return String message of deleted task.
     */
    public String getDeletedTaskMessage(Task deletedTask, int taskListSize) {
        return String.format("Noted. I've remeowved this task:\n\t%s\nNya-ow you have %d tasks in the list.",
                deletedTask, taskListSize);
    }

    /**
     * Returns marked task message.
     *
     * @param markedTask Marked Task to be printed.
     * @return String message of marked task.
     */
    public String getMarkedTaskMessage(Task markedTask) {
        return "Ameowzing! I've marked this task as done:\n\t" + markedTask;
    }

    /**
     * Returns unmarked task message.
     *
     * @param unmarkedTask Unmarked Task to be printed.
     * @return String message of unmarked task.
     */
    public String getUnmarkedTaskMessage(Task unmarkedTask) {
        return "OK, I've marked this task as not done yet:\n\t" + unmarkedTask;
    }

    /**
     * Returns tagged task message.
     *
     * @param taggedTask Tagged Task to be printed.
     * @return String message of Tagged task.
     */
    public String getTaggedTaskMessage(Task taggedTask) {
        return "Ameowzing! I've tagged this task:\n\t" + taggedTask;
    }

    /**
     * Returns untagged task message.
     *
     * @param untaggedTask Untagged Task to be printed.
     * @return String message of untagged task.
     */
    public String getUntaggedTaskMessage(Task untaggedTask) {
        return "OK, I've removed the tag for this task:\n\t" + untaggedTask;
    }

    /**
     * Prints the TaskList in order.
     *
     * @param taskArrayList The ArrayList of Tasks to be printed.
     * @param noTasksMessage String message when there are no tasks in the list.
     * @param introMessage String message before the list of tasks.
     * @return String message of list.
     */
    public String getListMessage(ArrayList<Task> taskArrayList, String noTasksMessage, String introMessage) {
        if (taskArrayList.size() == 0) {
            return noTasksMessage;
        } else {
            String listString = introMessage;
            for (int i = 1; i <= taskArrayList.size(); i++) {
                listString += i + ". " + taskArrayList.get(i - 1);
                if (i < taskArrayList.size()) {
                    listString += "\n";
                }
            }
            return listString;
        }
    }
}
