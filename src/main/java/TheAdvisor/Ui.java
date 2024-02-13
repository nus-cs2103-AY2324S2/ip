package theadvisor;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The Ui class represents the user interface for interacting with the application.
 * It handles user input, displays messages, and prints the task list.
 */
public class Ui {
    /**
     * Displays an introduction message for the user.
     *
     * @return The introduction message.
     */
    public static String intro() {
        return "Hello, I am The Advisor. The one and only advisor you will ever need in your investing "
                + "journey. What can I do for you?";
    }

    /**
     * Displays a goodbye message.
     *
     * @return The goodbye message.
     */
    public String goodbye() {
        return "Goodbye. Thank you for using TheAdvisor chatbox and I hope that my advice has managed"
                + " to help you in your investing journey!";
    }

    /**
     * Prints the tasks in the provided task list.
     *
     * @param taskList The task list to be printed.
     * @return The formatted string representation of the task list.
     */
    public String printList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTaskList();
        if (tasks.isEmpty()) {
            return "Sorry, there are no tasks in your list. Start adding them :)";
        } else {
            return "Here are the tasks in your list: \n"
                    + tasks.stream()
                            .map(task -> tasks.indexOf(task) + 1 + ". " + task)
                            .collect(Collectors.joining("\n"));
        }
    }
}
