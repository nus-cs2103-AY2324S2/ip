package linus;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents all the interactions with the user.
 */
public class Ui {
    public String findTasks(TaskList taskList, String keyword) {
        ArrayList<Task> matchingTasks = taskList.findMatchingTasks(keyword);
        String returnedStringOfTasks;

        if (matchingTasks.isEmpty()) {
            returnedStringOfTasks = "No matching tasks found.";
        } else {
            returnedStringOfTasks = "Here are the matching tasks in your list:\n";

            for (int i = 0; i < matchingTasks.size(); i++) {
                returnedStringOfTasks = returnedStringOfTasks + i + ". " + matchingTasks.get(i) + "\n";
            }
        }

        return returnedStringOfTasks;
    }
}
