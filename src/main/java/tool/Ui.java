package tool;

import java.util.Scanner;

import task.Task;

/**
 * Represents the tool to deal with user interactions.
 */
public class Ui {
    private static final String DIVIDER = "        ------------------------------------------------------------";

    private static final String POSSIBLE_COMMANDS = "        TODO     --- todo [task name]\n"
            + "        DEADLINE --- deadline [task name] /by [yyyy-mm-dd HH:MM]\n"
            + "        EVENT    --- event [task name] /from [yyyy-mm-dd HH:MM] /to [yyyy-mm-dd HH:MM]" ;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
    }

    /**
     * Prints greetings to user.
     */
    public void greetUser() {
        System.out.println(DIVIDER);
        System.out.println("        Hello! I'm Chronos.");
        System.out.println("        What can I do for you?");
        System.out.println(DIVIDER);
    }

    /**
     * Prints goodbye to user.
     */
    public static void bidGoodbye() {
        System.out.println(DIVIDER);
        System.out.println("        Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    /**
     * Prints task list.
     *
     * @param tasks List of tasks.
     */
    public void printTasks(TaskList tasks) {
        System.out.println(DIVIDER);
        System.out.println("        Here are the tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; i++) {
            task.Task currentTask = tasks.getTask(i - 1);
            System.out.println("        " + i + ". " + currentTask.toString());
        }
        System.out.println(DIVIDER);
    }

    /**
     * Prints no outstanding tasks and list of commands.
     */
    public void printNoOutstandingTasks() {
        System.out.println(DIVIDER);
        System.out.println("        There are no outstanding tasks in your list.\n");
        System.out.println("        You may add various tasks with the commands below:\n" + POSSIBLE_COMMANDS);
        System.out.println(DIVIDER);
    }

    /**
     * Prints list of commands.
     */
    public void printHelp() {
        System.out.println(DIVIDER);
        System.out.println("        Here are the possible commands:\n" + POSSIBLE_COMMANDS);
        System.out.println(DIVIDER);
    }

    /**
     * Prints success message of adding a todo task.
     *
     * @param todo Todo object added.
     * @param noOfTasks Number of tasks in the list.
     */
    public void printAddTodoSuccessful(Task todo, int noOfTasks) {
        System.out.println(DIVIDER);
        System.out.println("        Got it. I've added this task:");
        System.out.println("          " + todo);
        System.out.println("        Now you have " + noOfTasks + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    /**
     * Prints success message of adding a deadline.
     *
     * @param deadline Deadline object added.
     * @param noOfTasks Number of tasks in the list.
     */
    public void printAddDeadlineSuccessful(Task deadline, int noOfTasks) {
        System.out.println(DIVIDER);
        System.out.println("        Got it. I've added this task:");
        System.out.println("          " + deadline);
        System.out.println("        Now you have " + noOfTasks + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    /**
     * Prints success message of adding an event.
     *
     * @param event Event object added.
     * @param noOfTasks Number of tasks in the list.
     */
    public void printAddEventSuccessful(Task event, int noOfTasks) {
        System.out.println(DIVIDER);
        System.out.println("        Got it. I've added this task:");
        System.out.println("          " + event);
        System.out.println("        Now you have " + noOfTasks + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    /**
     * Prints success message of marking a task as completed.
     *
     * @param selectedTaskToBeMarked Selected task to be marked as completed.
     */
    public void printMarkTaskSuccessful(Task selectedTaskToBeMarked) {
        System.out.println(DIVIDER);
        System.out.println("        Nice! I've marked this task as done:");
        System.out.println("          " + selectedTaskToBeMarked);
        System.out.println(DIVIDER);
    }

    /**
     * Prints success message of unmarking a task as incomplete.
     *
     * @param selectedTaskToBeUnmarked Selected task to be marked as completed.
     */
    public void printUnmarkTaskSuccessful(Task selectedTaskToBeUnmarked) {
        System.out.println(DIVIDER);
        System.out.println("        OK, I've marked this task as not done yet:");
        System.out.println("          " + selectedTaskToBeUnmarked);
        System.out.println(DIVIDER);
    }

    /**
     * Prints success message of deleting a task.
     *
     * @param deletedTask Selected task to be deleted.
     * @param noOfTasks Number of tasks in the list.
     */
    public void printDeleteTaskSuccessful(Task deletedTask, int noOfTasks) {
        System.out.println(DIVIDER);
        System.out.println("        Noted. I've removed this task:");
        System.out.println("          " + deletedTask);
        System.out.println("        Now you have " + (noOfTasks - 1) + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    /**
     * Prints divider.
     */
    public void showDivider() {
        System.out.println(DIVIDER);
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
        System.out.println(DIVIDER);
        System.out.println("        Task number is not an integer. Please include a valid task number.");
        System.out.println(DIVIDER);
    }

    /**
     * Prints custom error message for IndexOutOfBoundsException.
     */
    public void printIndexOutOfBoundsException() {
        System.out.println(DIVIDER);
        System.out.println("        Task number out of range. Please include a valid task number.");
        System.out.println(DIVIDER);
    }
}
