package Duke.Ui;

import Duke.Task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class displays the messages and error to the user.
 * @author Tang Hao Liang
 */
public class Ui {
    private static Scanner in;

    /**
     * Constructor to start the scanning of user inputs.
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Prints the welcome message.
     */
    public static void showWelcome() {
        String start = "Hello! I'm Unknown \n"
                + "What can I do for you? \n";
        printString(start);
    }

    /**
     * Prints the end message.
     */
    public static void showEnd() {
        String end = "Bye. Hope to see you again soon!\n";
        printString(end);
    }

    /**
     * Prints the loading error message.
     */
    public static void showLoadingError() {
        String error = "Error when loading file\n";
        printString(error);
    }

    /**
     * Prints error messages.
     *
     * @param error Error messages.
     */
    public static void showError(String error) {
        printString(error);
    }

    /**
     * Reads user's input.
     *
     * @return User's input.
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Prints the text with bars above and below.
     *
     * @param str Text to be printed.
     */
    public static void printString(String str) {
        //Function to add the line in front and behind the text
        String lnBreak = "_______________________________________________________________\n";
        System.out.println(lnBreak + str + lnBreak);
    }

    /**
     * Prints all tasks in the list.
     *
     * @param taskList List of tasks.
     */
    public static void printList(ArrayList<Task> taskList) {
        //Function to produce the string for the list to be printed
        String out = "Here are the tasks in your list:\n";
        for (int i = 1; i < taskList.size() + 1; i++) {
            out += i + "." + taskList.get(i - 1) + "\n";
        }
        printString(out);
    }

    /**
     * Prints when tasks has been added.
     *
     * @param task Added task.
     * @param size Size of List.
     */
    public static void printAdd(String task, int size) {
        printString("Got it. I've added this task: \n" + task + "\nNow you have " + size + " tasks in the list.\n");
    }

    /**
     * Prints when tasks are marked.
     *
     * @param task Marked Task.
     */
    public static void printMark(String task) {
        printString("Nice! I've marked this task as done\n" + "  " + task + "\n");
    }

    /**
     * Prints when tasks are unmarked.
     *
     * @param task Unmarked Task.
     */
    public static void printUnmark(String task) {
        printString("OK, I've marked this task as not done yet\n" + "  " + task + "\n");
    }

    /**
     * Prints when tasks are deleted.
     *
     * @param task Deleted Task.
     * @param num Number of remaining task.
     */
    public static void printDelete(String task, int num) {
        printString("Noted. I've removed this task: \n" + task + "\nNow you have " + num + " tasks in the list.\n");
    }
}
