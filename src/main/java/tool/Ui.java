package tool;

import java.util.Scanner;

import task.Task;

/**
 * Represents the tool to deal with user interactions.
 */
public class Ui {
    private static final String DIVIDER = "------------------------------------------------------------";

    private static final String POSSIBLE_COMMANDS = "TODO     --- todo [task name]\n"
            + "\t\tDEADLINE --- deadline [task name] /by [yyyy-mm-dd HH:MM]\n"
            + "\t\tEVENT    --- event [task name] /from [yyyy-mm-dd HH:MM] /to [yyyy-mm-dd HH:MM]";

    /**
     * Constructs a Ui object.
     */
    public Ui() {
    }

    /**
     * Prints greetings to user.
     */
    public void greetUser() {
        System.out.println("\t\t" + DIVIDER);
        System.out.println("\t\tHello! I'm Chronos.");
        System.out.println("\t\tWhat can I do for you?");
        System.out.println("\t\t" + DIVIDER);
    }

    /**
     * Prints goodbye to user.
     */
    public static void bidGoodbye() {
        System.out.println("\t\t" + DIVIDER);
        System.out.println("\t\tBye. Hope to see you again soon!");
        System.out.println("\t\t" + DIVIDER);
    }

    /**
     * Prints task list.
     *
     * @param tasks List of tasks.
     */
    public void printTasks(TaskList tasks) {
        System.out.println("\t\t" + DIVIDER);
        System.out.println("\t\tHere are the tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; i++) {
            task.Task currentTask = tasks.getTask(i - 1);
            System.out.println("\t\t" + i + ". " + currentTask.toString());
        }
        System.out.println("\t\t" + DIVIDER);
    }

    /**
     * Prints filtered task list.
     *
     * @param tasks List of tasks with the specific keyword.
     */
    public void printFilteredTasks(TaskList tasks) {
        System.out.println("\t\t" + DIVIDER);
        System.out.println("\t\tHere are the matching tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; i++) {
            task.Task currentTask = tasks.getTask(i - 1);
            System.out.println("\t\t" + i + ". " + currentTask.toString());
        }
        System.out.println("\t\t" + DIVIDER);
    }

    /**
     * Prints no outstanding tasks and list of commands.
     */
    public void printNoOutstandingTasks() {
        System.out.println("\t\t" + DIVIDER);
        System.out.println("\t\tThere are no outstanding tasks in your list.\n");
        System.out.println("\t\tYou may add various tasks with the commands below:\n\t\t" + POSSIBLE_COMMANDS);
        System.out.println("\t\t" + DIVIDER);
    }

    /**
     * Prints list of commands.
     */
    public void printHelp() {
        System.out.println("\t\t" + DIVIDER);
        System.out.println("\t\tHere are the possible commands:\n\t\t" + POSSIBLE_COMMANDS);
        System.out.println("\t\t" + DIVIDER);
    }

    /**
     * Prints success message of adding a todo task.
     *
     * @param todo Todo object added.
     * @param noOfTasks Number of tasks in the list.
     */
    public void printAddTodoSuccessful(Task todo, int noOfTasks) {
        System.out.println("\t\t" + DIVIDER);
        System.out.println("\t\tGot it. I've added this task:");
        System.out.println("\t\t" + todo);
        System.out.println("\t\tNow you have " + noOfTasks + " tasks in the list.");
        System.out.println("\t\t" + DIVIDER);
    }

    /**
     * Prints success message of adding a deadline.
     *
     * @param deadline Deadline object added.
     * @param noOfTasks Number of tasks in the list.
     */
    public void printAddDeadlineSuccessful(Task deadline, int noOfTasks) {
        System.out.println("\t\t" + DIVIDER);
        System.out.println("\t\tGot it. I've added this task:");
        System.out.println("\t\t" + deadline);
        System.out.println("\t\tNow you have " + noOfTasks + " tasks in the list.");
        System.out.println("\t\t" + DIVIDER);
    }

    /**
     * Prints success message of adding an event.
     *
     * @param event Event object added.
     * @param noOfTasks Number of tasks in the list.
     */
    public void printAddEventSuccessful(Task event, int noOfTasks) {
        System.out.println("\t\t" + DIVIDER);
        System.out.println("\t\tGot it. I've added this task:");
        System.out.println("\t\t" + event);
        System.out.println("\t\tNow you have " + noOfTasks + " tasks in the list.");
        System.out.println("\t\t" + DIVIDER);
    }

    /**
     * Prints success message of marking a task as completed.
     *
     * @param selectedTaskToBeMarked Selected task to be marked as completed.
     */
    public void printMarkTaskSuccessful(Task selectedTaskToBeMarked) {
        System.out.println("\t\t" + DIVIDER);
        System.out.println("\t\tNice! I've marked this task as done:");
        System.out.println("\t\t" + selectedTaskToBeMarked);
        System.out.println("\t\t" + DIVIDER);
    }

    /**
     * Prints success message of unmarking a task as incomplete.
     *
     * @param selectedTaskToBeUnmarked Selected task to be marked as completed.
     */
    public void printUnmarkTaskSuccessful(Task selectedTaskToBeUnmarked) {
        System.out.println("\t\t" + DIVIDER);
        System.out.println("\t\tOK, I've marked this task as not done yet:");
        System.out.println("\t\t" + selectedTaskToBeUnmarked);
        System.out.println("\t\t" + DIVIDER);
    }

    /**
     * Prints success message of deleting a task.
     *
     * @param deletedTask Selected task to be deleted.
     * @param noOfTasks Number of tasks in the list.
     */
    public void printDeleteTaskSuccessful(Task deletedTask, int noOfTasks) {
        System.out.println("\t\t" + DIVIDER);
        System.out.println("\t\tNoted. I've removed this task:");
        System.out.println("\t\t" + deletedTask);
        System.out.println("\t\tNow you have " + (noOfTasks - 1) + " tasks in the list.");
        System.out.println("\t\t" + DIVIDER);
    }

    /**
     * Prints divider.
     */
    public void showDivider() {
        System.out.println("\t\t" + DIVIDER);
    }

    /**
     * Reads the next line received by the Scanner object.
     *
     * @return String representation of the next line.
     */
    public String readCommand(Scanner sc) {
        return sc.nextLine();
    }

    /**
     * Prints custom error message for NumberFormatException.
     */
    public void printNumberFormatException() {
        System.out.println("\t\t" + DIVIDER);
        System.out.println("\t\tTask number is not an integer. Please include a valid task number.");
        System.out.println("\t\t" + DIVIDER);
    }

    /**
     * Prints custom error message for IndexOutOfBoundsException.
     */
    public void printIndexOutOfBoundsException() {
        System.out.println("\t\t" + DIVIDER);
        System.out.println("\t\tTask number out of range. Please include a valid task number.");
        System.out.println("\t\t" + DIVIDER);
    }
}
