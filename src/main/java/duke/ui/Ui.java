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
    private final String break_line = "_____________________________________________________\n";

    /**
     * Initializes a Ui instance with a new Scanner object to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
    public String showHello() {
        return break_line
                + " Hello! I'm Your Only Friend\n"
                + " What can I do for you?\n"
                + break_line;
    }

    /**
     * Displays the goodbye message to the user.
     *
     * @return
     */
    public String showBye() {
        return break_line
                + " Bye. Hope to see you again soon!\n - Your Only Friend\n"
                + break_line;
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to be displayed.
     */
    public String showError(String message) {
        return break_line + message + break_line;
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
    public String showList(TaskList tasks) {
        String str1 = break_line + " Here are the tasks in your list:\n";
        String str2 = "";
        for (int i = 1; i <= tasks.getSize(); i++) {
            str2 += " " + i + "." + tasks.getTasks().get(i - 1).toString() + "\n";
        }
        String str_out = str1 + str2 + break_line;
        return str_out;
    }

    /**
     * Displays a specific task to the user along with a custom message.
     * @param msg The message to be displayed above the task.
     * @param task The task to be displayed.
     */
    public String showTask(String msg, Task task) {
        return break_line + msg + "\n"
                + task.toString()
                + break_line;
    }

    /**
     * Displays a specific task to the user along with a custom message, and
     * shows the total number of tasks in the list.
     *
     * @param msg   The message to be displayed above the task.
     * @param task  The task to be displayed.
     * @param tasks The TaskList to count the total number of tasks from.
     * @return
     */
    public String showTaskWithNum(String msg, Task task, TaskList tasks) {
        return break_line + msg + "\n" + task.toString()
                + "Now you have " + tasks.getSize()
                + (tasks.getSize() <= 1 ? " task in the list." : " tasks in the list.\n")
                + break_line;
    }

    /**
     * Displays list of tasks which includes the keyword for "finding tasks".
     * @param tasks
     */
    public String showMatchingList(TaskList tasks) {
        String str = "";
        for (int i = 1; i <= tasks.getSize(); i++) {
            str += " " + i + "." + tasks.getTasks().get(i - 1).toString() + "\n";
        }
        return break_line + " Here are the matching tasks in your list:\n" + str + break_line;
    }
}
