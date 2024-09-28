package linus;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents all the interactions with the user.
 */
public class Ui {
    /**
     * Returns String representation of all the matching tasks.
     *
     * @param taskList TaskList to be traversed for find command.
     * @param keyword Keyword to find matching tasks.
     * @return String representation of all the matching tasks.
     */
    public String findTasks(TaskList taskList, String keyword) {
        ArrayList<Task> matchingTasks = taskList.findMatchingTasks(keyword);
        String returnedStringOfTasks;

        if (matchingTasks.isEmpty()) {
            returnedStringOfTasks = "No matching tasks found.";
        } else {
            assert matchingTasks.size() >= 1 : "There needs to be a task to find!";

            returnedStringOfTasks = "Here are the matching tasks in your list:\n";

            for (int i = 0; i < matchingTasks.size(); i++) {
                returnedStringOfTasks = returnedStringOfTasks + i + ". " + matchingTasks.get(i) + "\n";
            }
        }

        return returnedStringOfTasks;
    }
}
