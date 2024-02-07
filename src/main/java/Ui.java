package tool;

import java.util.ArrayList;
import java.util.Scanner;

import tool.TaskList;

/**
 * Represents the tool to deal with user interactions.
 */
public class Ui {
    private static final String DIVIDER = "        ------------------------------------------------------------";

    private static final String POSSIBLE_COMMANDS = "        TODO     --- todo [task name]\n" +
                                                    "        DEADLINE --- deadline [task name] /by [yyyy-mm-dd HH:MM]\n" +
                                                    "        EVENT    --- event [task name] /from [yyyy-mm-dd HH:MM] /to [yyyy-mm-dd HH:MM]" ;

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
     * @param noOfTasks Number of tasks in list.
     * @param tasks List of tasks.
     */
    public void printTasks(int noOfTasks, TaskList tasks) {
        System.out.println(DIVIDER);
        System.out.println("        Here are the tasks in your list:");
        for (int i = 1; i < noOfTasks + 1; i++) {
            task.Task currentTask = tasks.getTask(i - 1);
            System.out.println("        " + i + ". " + currentTask.toString());
        }
        System.out.println(DIVIDER);
    }

    /**
     * Prints list of commands.
     */
    public void printHelp() {
        System.out.println(DIVIDER);
        System.out.println("        There are no outstanding tasks in your list.\n");
        System.out.println("        You may add various tasks with the commands below:\n" + POSSIBLE_COMMANDS);
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
