package duke.ui;

import java.util.Scanner;

import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * Handles the user interface of the Duke application. It is responsible for
 * all the interactions with the user, including taking input and showing
 * messages to the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Initializes a Ui instance with a new Scanner object to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showHello() {
        System.out.println("____________________________________________________________\n"
                + " Hello! I'm Your Only Friend\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n");
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showBye() {
        System.out.println("____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n - Your Only Friend\n"
                + "____________________________________________________________\n");
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to be displayed.
     */
    public void showError(String message) {

        System.out.println("____________________________________________________________\n"
                + message
                + "____________________________________________________________\n");
    }

    /**
     * Reads a command from the user.
     * @return The user's command as a trimmed, lowercase string.
     */
    public String readCommand() {
        return scanner.nextLine().trim().toLowerCase();
    }

    /**
     * Displays the list of tasks to the user.
     * @param tasks The TaskList containing the tasks to be displayed.
     */
    public void showList(TaskList tasks) {
        System.out.println("____________________________________________________________\n");
        System.out.println(" Here are the tasks in your list:\n");
        for (int i = 1; i <= tasks.getSize(); i++) {
            System.out.println(" " + i + "." + tasks.getTasks().get(i - 1).toString());
        }
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Displays a specific task to the user along with a custom message.
     * @param msg The message to be displayed above the task.
     * @param task The task to be displayed.
     */
    public void showTask(String msg, Task task) {
        System.out.println("____________________________________________________________\n");
        System.out.println(msg + "\n");
        System.out.println(task.toString());
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Displays a specific task to the user along with a custom message, and
     * shows the total number of tasks in the list.
     * @param msg The message to be displayed above the task.
     * @param task The task to be displayed.
     * @param tasks The TaskList to count the total number of tasks from.
     */
    public void showTaskWithNum(String msg, Task task, TaskList tasks) {
        System.out.println("____________________________________________________________\n");
        System.out.println(msg + "\n");
        System.out.println(task.toString());
        System.out.println(" Now you have " + tasks.getSize()
                + (tasks.getSize() <= 1 ? " task in the list." : " tasks in the list.\n"));
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Displays list of tasks which includes the keyword for "finding tasks".
     * @param tasks
     */
    public void showMatchingList(TaskList tasks) {
        System.out.println("____________________________________________________________\n");
        System.out.println(" Here are the matching tasks in your list:\n");
        for (int i = 1; i <= tasks.getSize(); i++) {
            System.out.println(" " + i + "." + tasks.getTasks().get(i - 1).toString());
        }
        System.out.println("____________________________________________________________\n");
    }
}
