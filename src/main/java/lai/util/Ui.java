package lai.util;

import lai.task.Task;

import java.util.Scanner;

/**
 * Handles the user interface for the Lai application.
 * This class is responsible for printing messages to the user interface
 */
public class Ui {
    /**
     * Prints a message indicating a task has been marked as done.
     *
     * @param t The task that was marked as done.
     * @return Success message containing the marked task.
     */
    public static String printTaskMarked(Task t) {
        return "You actually did something? Marked done:\n" + t;
    }

    /**
     * Prints a message indicating a task has been marked as not done.
     *
     * @param t The task that was marked as not done.
     * @return Success message containing the unmarked task.
     */
    public static String printTaskUnmarked(Task t) {
        return "Come on now, don't be useless. Marked not done:\n" + t;
    }

    /**
     * Prints a message indicating a new task has been added.
     *
     * @param newTask The task that was added.
     * @param tasks   The current list of tasks, for displaying the total count.
     * @return Success message containing the added task and the total number of tasks.
     */
    public static String printTaskAdded(Task newTask, TaskList tasks) {
        return String.format("Added: %s\nTotal number of tasks: %s", newTask, tasks.size());
    }

    /**
     * Prints a message indicating a task has been deleted.
     *
     * @param t The task that was deleted.
     * @return Success message containing the deleted task.
     */
    public static String printTaskDeleted(Task t) {
        return String.format("I have deleted: %s", t);
    }

    /**
     * Lists all tasks currently in the task list.
     *
     * @param tasks The task list to be displayed.
     * @return String containing all the tasks listed down.
     */
    public static String listTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%s. %s%n", i + 1, tasks.get(i)));
        }

        return sb.toString();
    }

    /**
     * Prints an exception message specific to the Lai application.
     *
     * @param e The LaiException to be printed.
     * @return The exception message.
     */
    public static String printLaiException(LaiException e) {
        return e.toString();
    }

    /**
     * Prints a user-friendly message when a NumberFormatException occurs.
     *
     * @param e The NumberFormatException that was caught.
     * @return Message prompting the user to enter numbers only.
     */
    public static String printNumberFormatException(NumberFormatException e) {
        return "Error occurred: Numbers only, please.";
    }

    /**
     * Prints a user-friendly message when a IndexOutOfBoundsException occurs.
     *
     * @param e The IndexOutOfBoundsException that was caught.
     * @return Message informing the user that the index is invalid
     */
    public static String printIndexOutOfBoundsException(IndexOutOfBoundsException e) {
        return "Error occurred: The index you provided was invalid, use `list` to view which tasks exist again.";
    }
}
