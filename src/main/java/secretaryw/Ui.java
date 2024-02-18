package secretaryw;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents the user interface for the SecretaryW application.
 */
public class Ui {
    private static final String LINE = "-----------------------------------------\n";

    /**
     * Displays a horizontal line.
     * @return A string representing a horizontal line.
     */
    public String showLine() {
        return LINE;
    }

    /**
     * Displays a greeting message.
     */
    public void showGreeting() {
        System.out.println(LINE + "Whats up, SecretaryW at your service\n" + "How can I help you?\n" + LINE);
    }

    /**
     * Displays a farewell message.
     */
    public String showFarewell() {
        return LINE + "Bye. Hope to see you again soon!\n" + LINE;
    }

    /**
     * Displays the list of tasks.
     * @param taskList The list of tasks to display.
     */
    public String showTasks(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            String result =  LINE + "No tasks available\n" + LINE;
            return result;
        } else {
            String result = LINE + "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                result += (" " + (i + 1) + ". " + taskList.get(i) + "\n");
            }
            result = result + LINE;
            return result;
        }
    }

    /**
     * Retrieves user input.
     * @return The user's input as a string.
     */
    public String getUserInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            return scanner.nextLine();
        }
    }

    /**
     * Retrieves user input.
     * @return The user's input as a string.
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

    public String showMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return LINE + "No matching tasks found.\n" + LINE;
        } else {
            String result = LINE + "Here are the matching tasks in your list:\n";
            for (int i = 0; i < matchingTasks.size(); i++) {
                result += (" " + (i + 1) + ". " + matchingTasks.get(i)+ "\n");
            }
            result += LINE;
            return result;
        }
    }
}