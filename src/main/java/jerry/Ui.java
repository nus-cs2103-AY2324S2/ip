package jerry;

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

    public String showGoodbye() {
        return ("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public String showWrong() {
        return "Sorry, something went wrong";
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    public String showMessage(String message) {
        return message;
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

    public String showList(TaskList list) {
        ArrayList<Task> tasks = list.getTasks();
        String result = "Here are the tasks in your list:";
        for (int x = 0; x < tasks.size() ; x++) {
            result += '\n' + ((x + 1) + "." + tasks.get(x));
        }
        if (tasks.size() == 0) {
            result += "\n You have no items in your list.";
        }
        return result;
    }

    public String showMark(TaskList list, int taskIndex) {
        ArrayList<Task> tasks = list.getTasks();
        return ("Nice! I've marked this task as done:\n  " + tasks.get(taskIndex));
    }

    public String showUnmark(TaskList list, int taskIndex) {
        ArrayList<Task> tasks = list.getTasks();
        return ("OK, I've marked this task as not done yet:\n  " + tasks.get(taskIndex));
    }

    public String showDelete(TaskList list, Task removedTask) {
        ArrayList<Task> tasks = list.getTasks();
        String result = "I've removed this task:\n  " + removedTask;
        return result + ("\nNow you have " + (tasks.size()) + " tasks in the list.");
    }

    public String showAdded(Task task, TaskList tasks) {
        return ("Got it. I've added this task:\n  " + task) + ("\nNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Displays the tasks found by a search.
     *
     * @param tasks The list of tasks that match the search criteria.
     */
    public String showTaskSearchResults(ArrayList<Task> tasks) {
        String result = "Here are the matching tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            result += '\n' + ((i + 1) + "." + tasks.get(i));
        }
        return result;
    }
}
