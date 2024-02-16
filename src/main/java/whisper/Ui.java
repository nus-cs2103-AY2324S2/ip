package whisper;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The Ui class handles the user interface of the Whisper application, including input/output and displaying messages.
 */
public class Ui {
    private static final String LINE = "-------------------------------------------------\n";
    public static String NAME = "Whisper";

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcomeMsg() {
        printLine();
        System.out.println("Hello! I'm " + NAME + " , your personal chatbot!\n" +
                "What can I do for you?\n");
        printLine();
    }

    /**
     * Prints a line separator to the console.
     */
    public void printLine() {
        System.out.println(LINE);
    }

    /**
     * Prints an error message to the console.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void printError(String errorMessage) {
        System.out.println("\nError: " + errorMessage);
    }

    /**
     * Reads a user command from the console.
     *
     * @return The user-entered command.
     */
    public String inputCommand() {
        System.out.println("Enter your input: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }

    /**
     * Prints an error message for failed file loading.
     */
    public void printLoadFileError() {
        printError("Error loading the file.");
    }

    /**
     * Displays the list of tasks to the console.
     *
     * @param tasks The list of tasks to be displayed.
     */

    public void printTasks(ArrayList<Task> tasks) {
        printLine();
        System.out.println("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        printLine();
    }

    /**
     * Prints a message confirming the addition of a task.
     *
     * @param task       The task that was added.
     * @param totalTasks The total number of tasks after the addition.
     */
    public void printTaskAdded(Task task, int totalTask) {
        printLine();
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + totalTask + " tasks in the list.");
        printLine();
    }

    /**
     * Prints a message confirming the marking of a task as done.
     *
     * @param task The task that was marked as done.
     */
    public void printTaskAsDone(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done:\n" + task);
        printLine();
    }

    /**
     * Prints a message confirming the marking of a task as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void printTaskAsUndone(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as not done:\n" + task);
        printLine();
    }

    /**
     * Prints a message confirming the removal of a task.
     *
     * @param task       The task that was removed.
     * @param totalTasks The total number of tasks after the removal.
     */
    public void printRemovedTask(Task task, int totalTasks) {
        printLine();
        System.out.println("Noted! I've removed this task: \n" + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        printLine();
    }

    /**
     * Prints tasks that match a given keyword.
     *
     * @param matchingTasks The list of tasks that match the keyword.
     */
    public void printMatchingTasks(ArrayList<Task> matchingTasks) {
        printLine();
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching task found.\n");
        } else {
            System.out.println("Here are the matching tasks in your list! \n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.get(i));
            }
        }
        printLine();
    }

    /**
     * Displays the exit message when the application exits.
     */
    public void printExitMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}