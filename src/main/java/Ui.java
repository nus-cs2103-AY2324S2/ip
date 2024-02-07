package tool;

import java.util.ArrayList;
import java.util.Scanner;

import tool.TaskList;

public class Ui {
    private static final String DIVIDER = "        ------------------------------------------------------------";

    private static final String POSSIBLE_COMMANDS = "        TODO     --- todo [task name]\n" +
            "        DEADLINE --- deadline [task name] /by [yyyy-mm-dd HH:MM]\n" +
            "        EVENT    --- event [task name] /from [yyyy-mm-dd HH:MM] /to [yyyy-mm-dd HH:MM]" ;

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

    public void showDivider() {
        System.out.println(DIVIDER);
    }

    public String readCommand(Scanner sc) {
        return sc.nextLine();
    }

    /**
     * Prints error message for NumberFormatException.
     */
    public void printNumberFormatException() {
        System.out.println(DIVIDER);
        System.out.println("        Task number is not an integer. Please include a valid task number.");
        System.out.println(DIVIDER);
    }

    /**
     * Prints error message for IndexOutOfBoundsException.
     */
    public void printIndexOutOfBoundsException() {
        System.out.println(DIVIDER);
        System.out.println("        Task number out of range. Please include a valid task number.");
        System.out.println(DIVIDER);
    }
}
