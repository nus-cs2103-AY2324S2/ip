package luke.ui;

import luke.task.Task;
import luke.task.TaskList;

import java.util.Scanner;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    Scanner sc;
    /**
     * Constructs an Ui object, where the scanner is initialised.
     */
    public Ui() {
        sc = new Scanner(System.in);

    }

    /**
     * Prints the welcome message when the application starts.
     */
    public void welcome() {
        System.out.println("________________________________________________________________________");
        System.out.println("Hello! I'm Luke.Luke");
        System.out.println("What can I do for you?");
        System.out.println("________________________________________________________________________");

    }

    /**
     * Prints the goodbye message when the application exits.
     */
    public void goodbye() {
        System.out.println("________________________________________________________________________");
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("________________________________________________________________________");
    }

    /**
     * Prints the error message when an error occurs.
     *
     * @param message The error message to be printed.
     */
    public void getErrorMessage(String message) {
        System.out.println("________________________________________________________________________");
        System.out.println(message);
        System.out.println("________________________________________________________________________");
    }

    /**
     * Prints the list of tasks in the TaskList.
     *
     * @param tasks The TaskList to be printed.
     */
    public void printList(TaskList tasks) {
        System.out.println("________________________________________________________________________");
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                System.out.println(i + 1 + "." + tasks.get(i));

            }
        }
        System.out.println("________________________________________________________________________");
    }

    /**
     * Prints the task when the task just got marked or unmarked.
     *
     * @param task The task that is marked or unmarked.
     */
    public void printTaskMarked(Task task) {
        String status = "";
        if (task.getIsDone()) {
            status = "done:";
        } else {
            status = "not done yet:";
        }
        System.out.println("________________________________________________________________________");
        System.out.println("Nice! I've marked this task as " + status);
        System.out.println(task);
        System.out.println("________________________________________________________________________");
    }

    /**
     * Prints the task when added to the TaskList.
     *
     * @param task The task that is added.
     * @param size The size of the TaskList.
     */
    public void printTaskAdded(Task task, int size) {
        String taskStringType = "";
        if (size > 1) {
            taskStringType = "tasks";
        } else {
            taskStringType = "task";
        }

        System.out.println("________________________________________________________________________");
        System.out.println("Got it! I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " " + taskStringType + " in the list.");
        System.out.println("________________________________________________________________________");
    }

    /**
     * Prints the task when deleted from the TaskList.
     *
     * @param task The task that is deleted.
     * @param size The size of the TaskList.
     */
    public void printTaskDeleted(Task task, int size) {
        String taskStringType = "";
        if (size > 1) {
            taskStringType = "tasks";
        } else {
            taskStringType = "task";
        }

        System.out.println("________________________________________________________________________");
        System.out.println("Noted! I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " " + taskStringType + " in the list.");
        System.out.println("________________________________________________________________________");
    }

    /**
     * Reads the command from the user.
     *
     * @return The command from the user in String format after trimming.
     */
    public String readCommand() {
        return sc.nextLine().trim();

    }
}
