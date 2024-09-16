package badgpt.util;

import badgpt.tasks.Task;

import java.io.ByteArrayOutputStream;

/**
 * An extension of the Ui class which handles task-related interactions.
 */
public class TasksUi extends Ui {

    /**
     * Initialises the Scanner object to take in user input. Also sets console and error output to a specified output
     * stream to be read by the GUI.
     *
     * @param out The stream for console output.
     * @param err The stream for error output.
     */
    public TasksUi(ByteArrayOutputStream out, ByteArrayOutputStream err) {
        super(out, err);
    }

    /**
     * Prints the newly-added task.
     *
     * @param task The newly-added task.
     * @param numTasks The current number of tasks in the list.
     */
    public void printAddTask(Task task, int numTasks) {
        printLine();
        System.out.println("Added task: " + task);
        System.out.println("Now you have " + numTasks + " task(s) in the list.");
        printLine();
    }

    /**
     * Prints a task. This can also be used to print multiple tasks (e.g. listing tasks).
     *
     * @param task The task to print.
     * @param index The position of the task. If 0, no number will be printed next to the task.
     * @param total The total number of tasks to print.
     */
    public void printTask(Task task, int index, int total) {
        if (index == 1) {
            printLine();
            System.out.println("Here are the tasks in your list:");
        }

        if (total > 1) {
            System.out.println(index + ". " + task);
        } else {
            System.out.println(task);
        }

        if (index == total) {
            printLine();
        }
    }

    /**
     * Prints the outcome of the mark/unmark task operations.
     *
     * @param task The task operated on.
     * @param type The type of operation. If 0, it is the mark operation. Otherwise, it is the unmark operation.
     */
    public void printMarkUnmarkOutcome(Task task, int type) {
        printLine();
        System.out.println((type == 0 ? "Nice! I've marked this task as done:\n" : "wyd bro why undo\n") + task);
        printLine();
    }

    /**
     * Prints the deleted task.
     *
     * @param task The task which has been deleted.
     * @param numTasks The current number of tasks in the list.
     */
    public void printDelete(Task task, int numTasks) {
        printLine();
        System.out.println("This task has been removed: " + task);
        System.out.println("Now you have " + numTasks + " task(s) in the list.");
        System.out.println("No, what are you waiting for? Do it! Just do it!");
        printLine();
    }
}
