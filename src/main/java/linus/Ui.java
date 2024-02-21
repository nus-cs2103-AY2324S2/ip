package linus;

import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    public String getUserInput(Scanner scanner) {
        return scanner.nextLine();
    }

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
