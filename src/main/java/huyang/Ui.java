package huyang;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui class responsible for handling user interaction and displaying messages.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructor for the Ui class.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a greeting message.
     */
    public void greet() {
        System.out.println("Hello! I'm Huyang");
        System.out.println("What can I do for you?");
    }

    /**
     * Reads and returns user input as a string.
     *
     * @return The user input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints an error message.
     *
     * @param message The error message to be displayed.
     */
    public void printErrorMessage(String message) {
        System.err.println("Error: " + message);
    }

    /**
     * Prints a message for an unknown command.
     */
    public void printUnknownCommandMessage() {
        System.out.println("I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Displays a farewell message.
     */
    public void farewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a message for an added task and the current task count.
     *
     * @param task      The added task to be displayed.
     * @param taskCount The current task count to be displayed.
     */
    public void printAddedTask(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints a message for a marked task as done.
     *
     * @param task The task marked as done to be displayed.
     */
    public void printMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Prints a message for a marked task as not done yet.
     *
     * @param task The task marked as not done yet to be displayed.
     */
    public void printUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Prints a message for a deleted task and the current task count.
     *
     * @param task      The deleted task to be displayed.
     * @param taskCount The current task count to be displayed.
     */
    public void printDeletedTask(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks An ArrayList of tasks to be displayed.
     */
    public void showTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("You have no tasks in your list.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Displays a list of found tasks based on a keyword search.
     *
     * @param tasks An ArrayList of tasks containing the tasks found with the given keyword.
     */
    public void showFoundTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found with the given keyword.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }
}
