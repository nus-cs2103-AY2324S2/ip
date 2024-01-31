package duke;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Initializes a new scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input.
     *
     * @return the next line of user input.
     */
    public String readCommand() {
        System.out.println("User:");
        return scanner.nextLine();
    }

    /**
     * Closes the scanner.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Prints the welcome message.
     */
    private void botHeader() {
        System.out.print("DevGPT:\n\t");
    }

    /**
     * Provides bot name for the given messages.
     */
    private void botHeader(String s) {
        System.out.print("DevGPT " + s + ":\n\t");
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        botHeader();
        System.out.println("Hello! I'm DevGPT\n\tWhat can I do for you?");
    }

    /**
     * Prints the task list.
     *
     * @param tasks the list of tasks to be printed.
     */
    public void showTaskList(TaskList tasks) throws DukeException {
        if (tasks.getSize() == 0) {
            throw new DukeException("No tasks found.");
        }
        botHeader();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(doubleTab() + (i + 1) + "." + tasks.getTask(i));
        }
    }

    /**
     * Shows the task marked as done.
     *
     * @param markTask the task to be marked as done.
     */
    public void showDone(Task markTask) {
        botHeader();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(doubleTab() + markTask);
    }

    /**
     * Shows the task marked as undone.
     *
     * @param unmarkTask the task to be marked as undone.
     */
    public void showUnmark(Task unmarkTask) {
        botHeader();
        System.out.println("Got it! I've marked this task as not done yet");
        System.out.println(doubleTab() + unmarkTask);
    }

    /**
     * Shows that the task was deleted.
     *
     * @param deleteTask the task to be deleted.
     * @param size the number of tasks in the task list after deletion.
     */
    public void showDelete(Task deleteTask, int size) {
        botHeader();
        System.out.println("Poof! I've removed this task:");
        System.out.println(doubleTab() + deleteTask);
        System.out.println(doubleTab() + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Shows that the task was added.
     *
     * @param task the task to be added.
     * @param size the number of tasks in the task list after adding tasks.
     */
    public void showAddTask(Task task, int size) {
        botHeader();
        System.out.println("Got it. I've added this task:");
        System.out.println(doubleTab() + task);
        System.out.println(doubleTab() + "Now you have " + size + " tasks in the list.");
    }

    private String doubleTab() {
        return "\t\t";
    }

    /**
     * Shows the error message with error header.
     *
     * @param s the error message to be shown.
     */
    public void showError(String s) {
        botHeader("Error");
        System.out.println(s);
    }

    /**
     * Shows error message for invalid command.
     */
    public void commandNotUnderstood() {
        showError("Your message is not understood. Please use following:\n\t"
                + "todo <description>\n\t"
                + "deadline <description> /by <dd-mm-yyyy>\n\t"
                + "event <description> /from <dd-mm-yyyy> /to <dd-mm-yyyy>\n\t"
                + "list\n\t"
                + "done <index>\n\t"
                + "delete <index>\n\t"
                + "find <keyword>\n\t"
                + "bye");
    }

    /**
     * Shows bye message.
     */
    public void showBye() {
        botHeader();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
