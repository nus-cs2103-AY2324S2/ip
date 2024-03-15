package ui;

import java.util.ArrayList;

import task.Task;
import tasklist.TaskList;

/**
 * The Ui class provides messages returned by JerryBot that will interact with the user.
 */
public class Ui {
    public static final String HI_MESSAGE = "Hello! I'm JerryBot.\n What can I do for you?";
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    public static String printHiMessage() {
        return HI_MESSAGE;
    }

    public static String printByeMessage() {
        return BYE_MESSAGE;
    }

    public static String printTaskRemovalMessage(String deletedTaskString, int tasksLeft) {
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                deletedTaskString, tasksLeft);
    }

    public static String printAddTaskMessage(String taskString, int tasksLeft) {
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                taskString, tasksLeft);
    }

    public static String printMarkTaskAsDoneMessage(String taskString) {
        return String.format("Nice! I've marked this task as done:\n%s", taskString);
    }

    public static String printMarkTaskAsUndoneMessage(String taskString) {
        return String.format("Nooo! I've marked this task as undone:\n%s", taskString);
    }

    public static String printListMessage(TaskList taskArrayList) {
        StringBuilder listStringBuilder = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 1; i <= taskArrayList.getLastIdx(); i++) {
            listStringBuilder.append(i);
            listStringBuilder.append(". ");
            listStringBuilder.append(taskArrayList.getTaskByIdx(i - 1).toString());
            listStringBuilder.append("\n");
        }
        return listStringBuilder.toString();
    }

    public static String printFindMessage(TaskList taskArrayList, String description) {
        ArrayList<Task> tasksWithKeyword = taskArrayList.getTasksWithKeyword(description);
        StringBuilder findStringBuilder = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasksWithKeyword.size(); i++) {
            findStringBuilder.append(i + 1);
            findStringBuilder.append(". ");
            findStringBuilder.append(tasksWithKeyword.get(i).toString());
            findStringBuilder.append("\n");
        }
        return findStringBuilder.toString();
    }

}
