package zack.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import zack.ZackException;
import zack.tasks.Task;

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
     * @return
     */
    public String showAddedTask(Task task, int totalTasks) {
        return "Got it. I've added this task:\n  " + task + "\nNow you have " + totalTasks
                + " tasks in the list.";
    }

    /**
     * Shows a message when a task is deleted, along with the current number of tasks.
     *
     * @param task       The task that was removed.
     * @param totalTasks The total number of tasks after deletion.
     * @return
     */
    public String showDeletedTask(Task task, int totalTasks) {
        return "Noted. I've removed this task:\n" + task + "\nNow you have " + totalTasks
                + " tasks in the list.";
    }

    /**
     * Prints the goodbye message when the user exits the application.
     */
    public String showGoodbyeMessage() {
        return "Goodbye! Hope to see you again soon!";
    }

    /**
     * Shows a message when a task is marked as done or undone.
     *
     * @param task   The task that was marked.
     * @param isDone True if the task is marked as done, false otherwise.
     */
    public String showMarkedTask(Task task, boolean isDone) {
        if (isDone) {
            return "Nice! I've marked this task as done:\n" + task;
        } else {
            return "OK, I've marked this task as not done yet:\n" + task;
        }
    }

    /**
     * Displays a list of tasks to the user.
     *
     * @param tasks The list of tasks to display.
     */
    public String showTaskList(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Displays tasks on a specific date to the user.
     *
     * @param date  The date for which tasks are displayed.
     * @param tasks The list of tasks happening on the specified date.
     */
    public String showTasksOnDate(LocalDate date, ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Tasks on ").append(date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))).append(":\n");
        for (Task task : tasks) {
            sb.append(task).append("\n");
        }
        return sb.toString();
    }

    /**
     * Displays an error message when loading tasks fails.
     *
     * @param e The exception that occurred while loading tasks.
     */
    public void showLoadingError(ZackException e) {
        System.err.println("Error: " + e.getMessage()); // Display the exception message
    }

    /**
     * Displays a list of tasks that have been found based on a search operation.
     *
     * This method is used to display a list of tasks that match a search criteria
     * in the specified TaskList. If no matching tasks are found, it prints a message
     * indicating that no tasks were found. If matching tasks are found, it prints a
     * numbered list of those tasks with their details.
     *
     * @param foundTasks The TaskList containing the matching tasks to display.
     * @throws ZackException If there is an error or exception while displaying
     *                      the found tasks.
     */
    public String showFoundTasks(TaskList foundTasks) throws ZackException {
        StringBuilder sb = new StringBuilder();
        if (foundTasks.getSize() == 0) {
            sb.append("No matching tasks in your list!");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < foundTasks.getSize(); i++) {
                sb.append((i + 1)).append(".").append(foundTasks.getTask(i)).append("\n");
            }
        }
        return sb.toString();
    }
}
