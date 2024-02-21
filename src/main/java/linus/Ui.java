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

    public void findTasks(TaskList taskList, String keyword) {
        ArrayList<Task> matchingTasks = taskList.findMatchingTasks(keyword);

        if (matchingTasks.isEmpty()) {
            showMessage("No matching tasks found.");
        } else {
            showMessage("Here are the matching tasks in your list:");

            for (int i = 0; i < matchingTasks.size(); i++) {
                showMessage(i + ". " + matchingTasks.get(i));
            }
        }
    }
}
