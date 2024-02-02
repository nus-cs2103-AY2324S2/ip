package zack.util;

import zack.ZackException;
import zack.tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles user interaction and displays messages to the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message to the user.
     */
    public void showWelcome() {
        this.showLine();
        System.out.println("Hello! I'm Zack\nWhat can I do for you?");
        this.showNewLineLine();
    }

    /**
     * Reads the command from the user.
     *
     * @return The command input by the user.
     * @throws ZackException If the input is empty.
     */
    public String readCommand() throws ZackException {
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            this.showLine();
            throw new ZackException("The input cannot be empty.");
        }
        return input;
    }

    /**
     * Prints a line divider.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a line divider with an additional new line.
     */
    public void showNewLineLine() {
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Displays the specified error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println("OOPS!!! " + errorMessage);
    }

    /**
     * Shows a message when tasks are loaded, including the loaded tasks.
     *
     * @param tasks The list of loaded tasks.
     * @throws ZackException If there is an error in loading tasks.
     */
    public void showLoadedTasks(TaskList tasks) throws ZackException {
        if (tasks.getSize() == 0) {
            this.showLine();
            System.out.println("Task file successfully loaded but it is currently empty.");
            this.showNewLineLine();
            return;
        }
        this.showLine();
        System.out.println("Here are the tasks that I have loaded from storage:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i));
        }
        this.showNewLineLine();
    }

    /**
     * Shows a message when a task is added, along with the current number of tasks.
     *
     * @param task       The task that was added.
     * @param totalTasks The total number of tasks after adding.
     */
    public void showAddedTask(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Shows a message when a task is deleted, along with the current number of tasks.
     *
     * @param task       The task that was removed.
     * @param totalTasks The total number of tasks after deletion.
     */
    public void showDeletedTask(Task task, int totalTasks) {
        System.out.println("Noted. I've removed this task:\n" + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Prints the goodbye message when the user exits the application.
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Shows a message when a task is marked as done or undone.
     *
     * @param task   The task that was marked.
     * @param isDone True if the task is marked as done, false otherwise.
     */
    public void showMarkedTask(Task task, boolean isDone) {
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:\n" + task);
        } else {
            System.out.println("OK, I've marked this task as not done yet:\n" + task);
        }
    }

    /**
     * Displays a list of tasks to the user.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Displays tasks on a specific date to the user.
     *
     * @param date  The date for which tasks are displayed.
     * @param tasks The list of tasks happening on the specified date.
     */
    public void showTasksOnDate(LocalDate date, ArrayList<Task> tasks) {
        System.out.println("Tasks on " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    /**
     * Displays an error message when loading tasks fails.
     *
     * @param e The exception that occurred while loading tasks.
     */
    public void showLoadingError(ZackException e) {
        System.err.println("Error: " + e.getMessage()); // Display the exception message
    }

    public void showFoundTasks(TaskList foundTasks) throws ZackException {
        if (foundTasks.getSize() == 0) {
            System.out.println("No matching tasks in your list!");
        } else {
            System.out.println("Here are the matching tasks in your list:\n");
            for (int i = 0; i < foundTasks.getSize(); i++) {
                System.out.println((i + 1) + "." + foundTasks.getTask(i));
            }
        }
    }
}
