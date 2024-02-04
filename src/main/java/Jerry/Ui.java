package Jerry;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class handles interactions with the user, including reading input and displaying messages.
 * It abstracts the complexity of command-line input and output operations.
 */
public class Ui {
    private Scanner scanner;

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

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Closes the scanner, effectively terminating the ability to read further input from the user.
     * Should be called before the application exits to release system resources.
     */
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }

    public void showList(TaskList list) {
        ArrayList<Task> tasks = list.getTasks();
        System.out.println("Here are the tasks in your list:");
        for (int x = 0; x < tasks.size() ; x++) {
            System.out.println((x + 1) + "." + tasks.get(x));
        }
    }

    public void showMark(TaskList list, int taskIndex) {
        ArrayList<Task> tasks = list.getTasks();
        System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(taskIndex));
    }

    public void showUnmark(TaskList list, int taskIndex) {
        ArrayList<Task> tasks = list.getTasks();
        System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.get(taskIndex));
    }

    public void showDelete(TaskList list, Task removedTask) {
        ArrayList<Task> tasks = list.getTasks();
        System.out.println("I've removed this task:\n  " + removedTask);
        System.out.println("Now you have " + (tasks.size()) + " tasks in the list.");
    }

    public void showAdded(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:\n  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Displays the tasks found by a search.
     *
     * @param tasks The list of tasks that match the search criteria.
     */
    public void showTaskSearchResults(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }
}
