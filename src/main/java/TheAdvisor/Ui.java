package TheAdvisor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * The Ui class represents the user interface for interacting with the application.
 * It handles user input, displays messages, and prints the task list.
 */
public class Ui {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Displays an introduction message for the user.
     */
    public void intro() {
        System.out.println("Hello, I am The Advisor. The one and only advisor you will ever need in your investing " +
                "journey. What can I do for you?");
    }

    /**
     * Displays a goodbye message and exits the application.
     */
    public void goodbye() {
        System.out.println("Goodbye. Thank you for using TheAdvisor chatbox and I hope that my advice has managed" +
                " to help you in your investing journey!");
        System.exit(0);
    }

    /**
     * Reads and returns user input.
     *
     * @return The user input as a string.
     * @throws IOException If an I/O error occurs while reading the input.
     */
    public String getUserInput() throws IOException {
        return br.readLine();
    }

    /**
     * Prints the tasks in the provided task list.
     *
     * @param taskList The task list to be printed.
     */
    public void printList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTaskList();
        int counter = 1;
        if (tasks.size() == 0) {
            System.out.println("     Sorry, there are no tasks in your list. Start adding them :)");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("     " + counter + ". " + taskList.getTask(i).toString());
                counter++;
            }
        }
    }
}
