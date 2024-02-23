package jerry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class handles interactions with the user, including reading input and displaying messages.
 * It abstracts the complexity of command-line input and output operations.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a new Ui instance for handling user input and output.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command from the user.
     *
     * @return A string representing the user's input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Jerry.\nWhat can I do for you?");
    }

    /**
     * Displays the Bye message to the user.
     */
    public String showGoodbye() {
        return ("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a generic error message to the user.
     */
    public String showWrong() {
        return "Sorry, something went wrong";
    }

    /**
     * Displays a message to the user.
     * @param message The message to be shown.
     * @return The string message.
     */
    public String showMessage(String message) {
        return message;
    }

    /**
     * Closes the scanner, effectively terminating the ability to read further input from the user.
     * Should be called before the application exits to release system resources.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Displays the tasks as part of the list command.
     *
     * @param list The list of tasks to show.
     * @return String reply for the list of tasks
     */
    public String showList(TaskList list) {
        ArrayList<Task> tasks = list.getTasks();
        StringBuilder result = new StringBuilder("Here are the tasks in your list:");
        for (int x = 0; x < tasks.size() ; x++) {
            result.append('\n').append((x + 1)).append(".").append(tasks.get(x));
        }
        if (tasks.isEmpty()) {
            result.append("\n You have no items in your list.");
        }
        return result.toString();
    }

    /**
     * Display the marked message reply to the user.
     * @param list The list the user is interacting with.
     * @param taskIndex The index of the task that was marked.
     * @return The String reply.
     */
    public String showMark(TaskList list, int taskIndex) {
        ArrayList<Task> tasks = list.getTasks();
        return ("Nice! I've marked this task as done:\n  " + tasks.get(taskIndex));
    }

    /**
     * Display the unmarked message reply to the user.
     * @param list The list the user is interacting with.
     * @param taskIndex The index of the task that was unmarked.
     * @return The String reply.
     */
    public String showUnmark(TaskList list, int taskIndex) {
        ArrayList<Task> tasks = list.getTasks();
        return ("OK, I've marked this task as not done yet:\n  " + tasks.get(taskIndex));
    }

    /**
     * Display the delete message.
     * @param list The list the user is interacting with.
     * @param removedTask The task to remove.
     * @return The String reply.
     */
    public String showDelete(TaskList list, Task removedTask) {
        ArrayList<Task> tasks = list.getTasks();
        String result = "I've removed this task:\n  " + removedTask;
        return result + ("\nNow you have " + (tasks.size()) + " tasks in the list.");
    }

    /**
     * Display the add message.
     * @param task The task to remove.
     * @param tasks The list the user is interacting with.
     * @return The String reply.
     */
    public String showAdded(Task task, TaskList tasks) {
        return ("Got it. I've added this task:\n  " + task) + ("\nNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Displays the tasks found by a search.
     *
     * @param tasks The list of tasks that match the search criteria.
     * @return String reply for the list of tasks
     */
    public String showTaskSearchResults(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            result.append('\n').append((i + 1)).append(".").append(tasks.get(i));
        }
        return result.toString();
    }

    /**
     * Displays the tasks for a specific date.
     *
     * @param tasks The list of tasks that matches the date.
     * @param date The date to search for.
     * @return String reply for the list of tasks
     */
    public String showTasksForDate(ArrayList<Task> tasks, LocalDate date) {
        StringBuilder result = new StringBuilder("Tasks scheduled for " + date + ":\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1)).append(". ").append(tasks.get(i)).append('\n');
        }
        return result.toString();
    }
}
