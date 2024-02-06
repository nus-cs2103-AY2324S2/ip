package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * The UI component for the fanTASKtic.
 * Handles interactions with the user, such as reading commands and displaying messages.
 */
public class Ui {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private Scanner sc;

    /**
     * Constructs a Ui object and initializes the Scanner for user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads a command from the user.
     *
     * @return The user's input command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the welcome message when the chatbot starts.
     */
    public void welcomeMsg() {
        System.out.println("Hello from \n" + LOGO);
        System.out.println("This is fanTasktic!\n"
                + "What can I do for you?\n");
    }

    /**
     * Prints the goodbye message when the 'bye' command is entered.
     */
    public void goodbyeMsg() {
        System.out.println("Goodbye. Have a great day ahead!");
    }

    /**
     * Prints a message indicating that a task has been added successfully.
     *
     * @param task  The task that has been added.
     * @param index The current number of tasks in the list.
     */
    public void showAddMsg(Task task, int index) {
        System.out.println("Got it. I've added this task: \n" + task);
        System.out.format("Now you have %d tasks in the list.\n", index);
    }

    /**
     * Prints a message indicating that a task has been deleted successfully.
     *
     * @param deletedTask The task that has been deleted.
     * @param index       The current number of tasks in the list.
     */
    public void showDeleteMsg(Task deletedTask, int index) {
        System.out.println("Noted. I've removed this task:\n"
                + deletedTask);
        System.out.format("Now you have %d tasks in the list.\n", (index - 1));
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showMarkMsg(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    /**
     * Prints a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public void showUnmarkMsg(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    /**
     * Prints out a list of available commands.
     */
    public void showHelpMsg() {
        System.out.println("bye: Terminate the program.\n"
                + "list: Display the list of tasks.\n"
                + "mark <index>: Mark a task as done.\n"
                + "unmark <index>: Mark a task as not done.\n"
                + "todo <description>: Add a todo task.\n"
                + "deadline <description> /by <yyyy-MM-dd hh:mma>: Add a deadline task.\n"
                + "event <description> /from <startDate> /to <endDate>: Add an event task.\n"
                + "find <keyword>: Find a list of tasks that match with the keyword.");
    }

    /**
     * Prints an error message.
     *
     * @param e The error message to be displayed.
     */
    public void showError(String e) {
        System.out.println(e);
    }

    /**
     * Prints a message indicating the start of the task list.
     */
    public void showList() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Prints a line separator.
     */
    public void showLine() {
        System.out.println("-----------------------------------------------------------------");
    }

    /**
     * Prints a list of tasks that match the specified keyword.
     *
     * @param matchingTasks The list of tasks that match the keyword.
     */
    public void showFindMsg(ArrayList<Task> matchingTasks) {
        System.out.println("Here are the matching tasks in your list: ");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + "." + matchingTasks.get(i));
        }
    }

}
