package utils;

import java.util.ArrayList;
import java.util.Scanner;

import exceptions.ConvoBotException;

/**
 * The {@code UI} class provides methods for interacting with the user through the console.
 * It includes methods for reading user input, displaying messages, and presenting task-related information.
 */
public class UI {

    private static final String LEFT_PADDING = "    ";
    private final Scanner scanner;

    /**
     * Constructs a new {@code UI} instance with a {@code Scanner} for reading user input.
     */
    public UI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads user input from the console.
     *
     * @return the user input as a string
     */
    public String readUserInput() {
        return scanner.nextLine();
    }

    /**
     * Displays a horizontal line in the console.
     *
     * @param newline {@code true} if a newline should be added after the horizontal line, {@code false} otherwise
     */
    public void showHorizontalLine(boolean newline) {
        System.out.println(LEFT_PADDING + "____________________________________________________________");
        if (newline) {
            System.out.println();
        }
    }

    /**
     * Displays a welcome message in the console.
     */
    public void showWelcomeMsg() {
        showHorizontalLine(false);
        System.out.println(LEFT_PADDING + " Hello! I'm ConvoBot");
        System.out.println(LEFT_PADDING + " What can I do for you?");
        showHorizontalLine(true);
    }

    /**
     * Displays an exit message in the console.
     */
    public void showExitMsg() {
        showHorizontalLine(false);
        System.out.println(LEFT_PADDING + " Bye. Hope to see you again soon!");
        showHorizontalLine(true);
    }

    /**
     * Displays the task list in the console.
     *
     * @param tasks the task list to be displayed
     */
    public void showTaskList(TaskList tasks) {
        System.out.println(LEFT_PADDING + " " + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            try {
                System.out.println(LEFT_PADDING + " " + Integer.toString(index) + "." + tasks.getTaskString(i));
            } catch (ConvoBotException e) {
                // impossible to reach here
            }
        }
    }

    /**
     * Displays the matching tasks from the provided ArrayList of tasks.
     * Each task is printed with its corresponding index in the list.
     *
     * @param tasks An ArrayList of Strings containing the task strings to be displayed.
     *
     * @throws NullPointerException If the tasks ArrayList is null.
     */
    public void showMatchingTasks(ArrayList<String> tasks) {
        System.out.println(LEFT_PADDING + " " + "Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            System.out.println(LEFT_PADDING + " " + Integer.toString(index)
                    + "." + tasks.get(i));
        }
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param taskString the string representation of the added task
     * @param dbSize     the size of the task list after adding the task
     */
    public void showAdded(String taskString, int dbSize) {
        System.out.println(LEFT_PADDING + " " + "Got it. I've added this task:");
        System.out.println(LEFT_PADDING + "   " + taskString);
        System.out.println(LEFT_PADDING + " Now you have " + Integer.toString(dbSize) + " tasks in the list.");
    }

    /**
     * Displays a message indicating that a task has been removed.
     *
     * @param taskString the string representation of the removed task
     * @param dbSize     the size of the task list after removing the task
     */
    public void showRemoved(String taskString, int dbSize) {
        System.out.println(LEFT_PADDING + " " + "Noted. I've removed this task:");
        System.out.println(LEFT_PADDING + "   " + taskString);
        System.out.println(LEFT_PADDING + " Now you have " + Integer.toString(dbSize) + " tasks in the list.");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param taskString the string representation of the marked task
     */
    public void showMarked(String taskString) {
        System.out.println(LEFT_PADDING + " " + "Nice! I've marked this task as done:");
        System.out.println(LEFT_PADDING + " " + taskString);
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param taskString the string representation of the unmarked task
     */
    public void showUnmarked(String taskString) {
        System.out.println(LEFT_PADDING + " " + "OK, I've marked this task as not done yet:");
        System.out.println(LEFT_PADDING + " " + taskString);
    }

    /**
     * Displays an error message in the console.
     *
     * @param errorMsg the error message to be displayed
     */
    public void showError(String errorMsg) {
        System.out.println(LEFT_PADDING + " " + errorMsg);
    }
}
