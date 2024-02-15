package secretaryw;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents the user interface for the SecretaryW application.
 */
public class Ui {
    private static final String line = "-----------------------------------------------------------------\n";

    /**
     * Displays a horizontal line.
     * @return A string representing a horizontal line.
     */
    public String showLine() {
        return line;
    }

    /**
     * Displays a greeting message.
     */
    public void showGreeting() {
        System.out.println(line + "SecretaryW at your service\n" + "How can I help you?\n" + line);
    }

    /**
     * Displays a farewell message.
     */
    public void showFarewell() {
        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }

    /**
     * Displays the list of tasks.
     * @param taskList The list of tasks to display.
     */
    public void showTasks(ArrayList<Task> taskList) {
        System.out.println(line);
        if (taskList.isEmpty()) {
            System.out.println("No tasks available");
            System.out.println(line);
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + taskList.get(i));
            }
            System.out.println(line);
        }
    }

    /**
     * Retrieves user input.
     * @return The user's input as a string.
     */
    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Retrieves user input.
     * @return The user's input as a string.
     */
    public void showTaskAdded(Task task, int count) {
        System.out.println(line + "Got it. I've added this task:\n" + task);
        System.out.println(" Now you have " + count + " tasks in the list.\n" + line);
    }

    /**
     * Displays a message indicating a task has been deleted.
     * @param task The task that was deleted.
     * @param count The total number of tasks in the list after deletion.
     */
    public void showTaskDeleted(Task task, int count) {
        showLine();
        System.out.println("Noted. I've removed this task:\n" + task);
        System.out.println(" Now you have " + count + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a general message.
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println(line + message + "\n" + line);
    }
}