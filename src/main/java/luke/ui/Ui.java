package luke.ui;

import java.util.Scanner;

import luke.task.Task;
import luke.task.TaskList;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    private static final String LINE = "_________________________________________________________";
    private Scanner sc;

    /**
     * Constructs an Ui object, where the scanner is initialised.
     */
    public Ui() {
        sc = new Scanner(System.in);

    }

    /**
     * Prints the welcome message when the application starts.
     */
    public String welcome() {

        return LINE + "\nHello! I'm Luke!\nWhat can I do for you?\n" + LINE;
    }

    /**
     * Prints the goodbye message when the application exits.
     */
    public String goodbye() {
        return LINE + "\nBye! Hope to see you again soon!\n" + LINE;

    }

    /**
     * Prints the error message when an error occurs.
     *
     * @param message The error message to be printed.
     */
    public String getErrorMessage(String message) {
        return LINE + "\n" + message + "\n" + LINE;

    }

    /**
     * Prints the list of tasks in the TaskList.
     *
     * @param tasks The TaskList to be printed.
     */
    public String printList(TaskList tasks) {
        StringBuilder listString = new StringBuilder();
        listString = new StringBuilder(LINE + "\nHere are the tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                String taskString = i + 1 + "." + tasks.get(i);
                listString.append(taskString).append("\n");

            }
        }
        listString.append(LINE);
        return listString.toString();
    }

    /**
     * Prints the task when the task just got marked or unmarked.
     *
     * @param task The task that is marked or unmarked.
     */
    public String printTaskMarked(Task task) {
        String status = "";
        if (task.getIsDone()) {
            status = "done:";
        } else {
            status = "not done yet:";
        }

        return LINE + "\nNice! I've marked this task as " + status + "\n" + task + "\n" + LINE;

    }

    /**
     * Prints the task when added to the TaskList.
     *
     * @param task The task that is added.
     * @param size The size of the TaskList.
     */
    public String printTaskAdded(Task task, int size) {
        String taskStringType = "";
        if (size > 1) {
            taskStringType = "tasks";
        } else {
            taskStringType = "task";
        }

        return LINE + "\nGot it! I've added this task:\n" + task + "\nNow you have " + size + " "
                + taskStringType + " in the list.\n" + LINE;

    }

    /**
     * Prints the task when deleted from the TaskList.
     *
     * @param task The task that is deleted.
     * @param size The size of the TaskList.
     */
    public String printTaskDeleted(Task task, int size) {
        String taskStringType = "";
        if (size > 1) {
            taskStringType = "tasks";
        } else {
            taskStringType = "task";
        }

        return LINE + "\nNoted! I've removed this task:\n" + task + "\nNow you have " + size + " "
                + taskStringType + " in the list.\n" + LINE;

    }

    /**
     * Reads the command from the user.
     *
     * @return The command from the user in String format after trimming.
     */
    public String readCommand() {
        return sc.nextLine().trim();

    }

    public String printTaskFound(TaskList tasks) {
        StringBuilder foundString = new StringBuilder();
        foundString = new StringBuilder(LINE + "\nHere are the matching tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                String taskString = i + 1 + "." + tasks.get(i);
                foundString.append(taskString).append("\n");
            }
        }
        foundString.append(LINE);
        return foundString.toString();

    }
}
