package tyler.ui;

import java.util.ArrayList;
import java.util.Scanner;

import tyler.task.Task;
import tyler.task.TaskList;

/**
 * Represents a User Interface of Tyler.
 */
public class Ui {
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Prints welcome when initialize Tyler.
     */
    public String showWelcome() {
        return "    Hello from Tyler\n"
                + "    What can I do for you?\n"
                + "    list, todo, deadline, event, mark, unmark, bye\n"
                + "    --------------------------------------------------";
    }

    /**
     * Prints bye when exit Tyler.
     */
    public String showBye() {
        return "    Bye. Hope to see you again";
    }

    /**
     * Prints loading error.
     */
    public String showLoadingError() {
        return "    Can't load file / file does not exist. I'll create a new one for you.";
    }

    /**
     * Prints the task is added.
     *
     * @param task The specific task that have added.
     * @param num  The number of tasks after adding this task.
     */
    public String showTaskAdded(Task task, int num) {
        return "    Got it! I've added this task:\n"
                + "      " + task + "\n"
                + "    Now you have " + num + " tasks in the list";
    }

    /**
     * Prints the task is deleted.
     *
     * @param task The specific task that needed to be deleted.
     * @param num  The number of tasks after deleting this task.
     */
    public String showTaskDeleted(Task task, int num) {
        return "    Noted! I've deleted this task:\n"
                + "      " + task + "\n"
                + "    Now you have " + num + " tasks in the list";
    }

    /**
     * Read a line from System.in.
     *
     * @return A line of String
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the current taskList.
     *
     * @param tasks The taskList class that hold the taskList
     */
    public String list(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getList();
        String listOfTasks = "";
        for (int i = 1; i < taskList.size() + 1; i++) {
            if (taskList.get(i - 1) == null) {
                break;
            }
            String task = taskList.get(i - 1).toString();
            listOfTasks += "    " + i + ". " + task + "\n";
        }
        return listOfTasks;
    }

    /**
     * Prints error message.
     * @param message The message to be printed.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Prints dividing line.
     */
    public String showLine() {
        return "    --------------------------------------------------\n";
    }
}
