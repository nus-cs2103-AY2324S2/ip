package linus;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents all the interactions with the user.
 */
public class Ui {
    /**
     * Returns the next line of user input read from the Scanner.
     *
     * @param scanner Scanner object to read input.
     * @return Next line's String representation.
     */
    public String getUserInput(Scanner scanner) {
        return scanner.nextLine();
    }

    /**
     * Prints out the input String message.
     *
     * @param message Input String message to be printed out.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    public String findTasks(TaskList taskList, String keyword) {
        ArrayList<Task> matchingTasks = taskList.findMatchingTasks(keyword);
        String returnedStringOfTasks = "";

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            assert matchingTasks.size() >= 1 : "There needs to be a task to find!";

            returnedStringOfTasks = "Here are the matching tasks in your list:\n";

            for (int i = 0; i < matchingTasks.size(); i++) {
                returnedStringOfTasks = returnedStringOfTasks + i + ". " + matchingTasks.get(i) + "\n";
            }

            return returnedStringOfTasks;
        }
    }
}
