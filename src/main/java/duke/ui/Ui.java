package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * The UI component for the Decoder.
 * Handles interactions with the user, such as reading commands and displaying messages.
 */
public class Ui {

    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes the Scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message when the chatbot starts.
     *
     * @return A welcome message.
     */
    public String showWelcomeMsg() {
        return "Welcome to TaskFlow!\n" + "I am your virtual assistant, Taskie!\n"
                + "What can I do for you?\n";
    }

    /**
     * Prints the goodbye message when the 'bye' command is entered.
     *
     * @return A goodbye message.
     */
    public String showGoodbyeMsg() {
        return "Goodbye. Have a great day ahead!\n";
    }

    /**
     * Prints a message indicating that a task has been added successfully.
     *
     * @param task  The task that has been added.
     * @param index The current number of tasks in the list.
     * @return A message indicating that a task has been added successfully.
     */
    public String showAddMsg(Task task, int index) {
        return "Got it. I've added this task: \n" + task.toString() + "\n"
                + String.format("Now you have %d tasks in the list.\n", index);
    }

    /**
     * Prints a message indicating that a task has been deleted successfully.
     *
     * @param deletedTask The task that has been deleted.
     * @param index       The current number of tasks in the list.
     * @return A message indicating that a task has been deleted successfully.
     */
    public String showDeleteMsg(Task deletedTask, int index) {
        return "Noted. I've removed this task:\n" + deletedTask.toString() + "\n"
                + String.format("Now you have %d tasks in the list.\n", (index - 1));
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return a message indicating that a task has been marked as done.
     */
    public String showMarkMsg(Task task) {
        return "Nice! I've marked this task as done: \n" + task.toString() + "\n";
    }

    /**
     * Prints a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     * @return a message indicating that a task has been marked as not done.
     */
    public String showUnmarkMsg(Task task) {
        return "OK, I've marked this task as not done yet: \n" + task.toString() + "\n";
    }

    /**
     * Prints out a list of available commands.
     *
     * @return A list of available commands.
     */
    public String showHelpMsg() {
        return "bye: Terminate the program.\n"
                + "list: Display the list of tasks.\n"
                + "mark <index>: Mark a task as done.\n"
                + "unmark <index>: Mark a task as not done.\n"
                + "todo <description>: Add a todo task.\n"
                + "deadline <description> /by <yyyy-MM-dd hh:mma>: Add a deadline task.\n"
                + "event <description> /from <startDate> /to <endDate>: Add an event task.\n"
                + "find <keyword>: Find a list of tasks that match with the keyword.\n";
    }

    /**
     * Prints an error message.
     *
     * @param e The error message to be displayed.
     * @return the error message.
     */
    public String showError(String e) {
        return e;
    }

    /**
     * Prints a message indicating the start of the task list.
     *
     * @return A message indicating the start of the task list.
     */
    public String showList() {
        return "Here are the tasks in your list:\n";
    }

    /**
     * Prints a line separator.
     *
     * @return A line separator.
     */
    public String showLine() {
        return "----------------------------------------------------"
                + "\n";
    }

    /**
     * Prints a list of tasks that match the specified keyword.
     *
     * @param matchingTasks The list of tasks that match the keyword.
     * @return a list of matching tasks.
     */
    public String showFindMsg(ArrayList<Task> matchingTasks) {
        String s = "";
        s += "Here are the matching tasks in your list:\n";
        for (int i = 0; i < matchingTasks.size(); i++) {
            s += (i + 1) + "." + matchingTasks.get(i).toString() + "\n";
        }
        return s;
    }

    public String showArchiveMsg(Task task, int index) {
        return "This task has been archived successfully!\n"
                + task.toString() + "\n"
                + String.format("Now you have %d tasks in the archive list.\n", index)
                + "\n";
    }

    public String showUnarchiveMsg(Task task, int index) {
        return "This task has been unarchived successfully!\n"
                + task.toString() + "\n"
                + String.format("Now you have %d tasks in the archive list.\n", (index - 1))
                + "\n";
    }
}
