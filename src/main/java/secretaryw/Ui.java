package secretaryw;

import java.util.ArrayList;

/**
 * Represents the user interface for the SecretaryW application.
 */
public class Ui {
    private static final String LINE = "-----------------------------------------\n";

    /**
     * Displays the list of tasks.
     * @param taskList The list of tasks to display.
     */
    public String showTasks(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return LINE + "No tasks available\n" + LINE;
        } else {
            StringBuilder result = new StringBuilder(LINE + "Here are the tasks in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                result.append(" ").append(i + 1).append(". ").append(taskList.get(i)).append("\n");
            }
            result.append(LINE);
            return result.toString();
        }
    }

    /**
     * Displays a message indicating that a task has been added.
     * @param task The task that has been added.
     * @param count The total number of tasks after the addition.
     * @return A formatted string representing the task addition message.
     */
    public String showTaskAdded(Task task, int count) {
        return LINE + "Got it. I've added this task:\n" + task + "\n" +
                " Now you have " + count + " tasks in the list.\n" + LINE;
    }

    /**
     * Displays a general message.
     * @param message The message to display.
     */
    public String showMessage(String message) {
        return LINE + message + "\n" + LINE;
    }

    /**
     * Displays the list of matching tasks based on a search keyword.
     * @param matchingTasks The list of tasks that match the search keyword.
     * @return A formatted string representing the list of matching tasks.
     */
    public String showMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return LINE + "No matching tasks found.\n" + LINE;
        } else {
            StringBuilder result = new StringBuilder(LINE + "Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                result.append(" ").append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
            }
            result.append(LINE);
            return result.toString();
        }
    }
}