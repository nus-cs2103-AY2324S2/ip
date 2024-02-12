package tyler.ui;
import tyler.task.Task;
import tyler.task.TaskList;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents a User Interface of Tyler.
 */
public class Ui {
    Scanner sc = new Scanner(System.in);

    /**
     * Prints welcome when initialize Tyler.
     */
    public void showWelcome() {
        System.out.println("    Hello from Tyler");
        System.out.println("    What can I do for you?");
        System.out.println("    list, todo, deadline, event, mark, unmark, bye");
        System.out.println("    --------------------------------------------------");
    }

    /**
     * Prints bye when exit Tyler.
     */
    public void showBye() {
        System.out.println("    Bye. Hope to see you again");
    }

    /**
     * Prints loading error.
     */
    public void showLoadingError() {
        System.out.println("    Can't load file / file does not exist. I'll create a new one for you.");
    }

    /**
     * Prints the task is added.
     *
     * @param task The specific task that have added.
     * @param num  The number of tasks after adding this task.
     */
    public void showTaskAdded(Task task, int num) {
        System.out.println("    Got it! I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + num + " tasks in the list");
    }

    /**
     * Prints the task is deleted.
     *
     * @param task The specific task that needed to be deleted.
     * @param num  The number of tasks after deleting this task.
     */
    public void showTaskDeleted(Task task, int num) {
        System.out.println("    Noted! I've deleted this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + num + " tasks in the list");
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
    public void list(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getList();
        for (int i = 1; i < taskList.size() + 1; i++) {
            if (taskList.get(i - 1) == null) {
                break;
            }
            String task = taskList.get(i - 1).toString();
            System.out.println("    " + i + ". " + task);
        }
    }

    /**
     * Prints error message.
     * @param message The message to be printed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints dividing line.
     */
    public void showLine() {
        System.out.println("    --------------------------------------------------");
    }
}
