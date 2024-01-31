package headcube;
import java.util.List;
import java.util.Scanner;
/**
 * The Ui class handles the user interface for the HeadCube application.
 * It is responsible for displaying messages to the user.
 */
public class Ui {
    /**
     * Constructor of Ui object
     */
    public Ui() {
    }

    /**
     * Displays greeting message.
     */
    public void greet() {
        System.out.println("Hello! I'm HeadCube\n" + "What can I do for you?\n");
    }

    /**
     * Displays exit message.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    /**
     * Displays error message.
     *
     * @param message The error message to be displayed.
     */
    public void error(String message) {
        System.out.println(message);
    }

    /**
     * Displays saved message.
     */
    public void saveMessage() {
        System.out.println("Finished saving");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public void list(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        System.out.println();
    }

    public void showFoundTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
}
