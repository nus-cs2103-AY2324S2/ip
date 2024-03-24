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
        return "I am Madara Uchiha, the god of all Shinobi. Tell me, what do you seek.";
    }

    /**
     * Displays a goodbye message.
     *
     * @return The goodbye message.
     */
    public String goodbye() {
        return "Brave soul, may we meet again when our path meet. AMETERASU!!!";
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
            String angryEmoji = "\uD83D\uDCA2";
            return "There is nothing here! ARE YOU FOOLING ME!?!?" + angryEmoji;
        } else {
            return "Here are the tasks in your list: \n"
                    + tasks.stream()
                            .map(task -> tasks.indexOf(task) + 1 + ". " + task)
                            .collect(Collectors.joining("\n"));
        }
    }
}
