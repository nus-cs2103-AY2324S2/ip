package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.util.Scanner;

/**
 * Ui class represents the user interface for Duke.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs an Ui object with a Scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Duke. What can I do for you?");
    }

    /**
     * Gets user input from the command line.
     *
     * @return The user's input command.
     */
    public String getUserInput() {
        System.out.print("Enter your command: ");
        return scanner.next();
    }

    /**
     * Gets user input from the command line, allowing for multiple words.
     *
     * @return The user's input command as a String.
     */
    public String getUserInput3() {
        //System.out.print("Enter your command: ");
        return scanner.nextLine();
    }

    /**
     * Displays a message indicating a deleted command.
     *
     * @param str The command that was deleted.
     */
    public void deleteMessage(String str) {
        System.out.println("I have deleted the below command: \n" + str);
    }

    /**
     * Gets user input from the command line, expecting a single word.
     *
     * @return The user's input command as a String.
     */
    public String getUserInput2() {
        //System.out.print("Enter your command: ");
        return scanner.next();
    }

    /**
     * Gets user input as an integer from the command line.
     *
     * @return The user's input as an integer.
     */
    public int getUserInputInt() {
        return scanner.nextInt();
    }

    /**
     * Checks if the next input is an integer.
     *
     * @return True if the next input is an integer, false otherwise.
     */
    public boolean hasNextInt() {
        return scanner.hasNextInt();
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye! Hope to see you again soon.");
    }

    /**
     * Displays an error message.
     *
     * @param errorMessage The error message to display.
     */
    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    /**
     * Displays an error message for loading tasks.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file. Starting with an empty task list.");
    }

    /**
     * Displays a message indicating a marked task.
     *
     * @param task The task that was marked.
     */
    public void markedMessage(Task task) {
        System.out.println("\t" + "Nice! I've marked this task " +
                "as done:" + "\n" +
                "\t " + task);
    }

    /**
     * Displays a message to the user.
     *
     * @param str The message to display.
     */
    public void showMessage(String str) {
        System.out.println(str);
    }
}

