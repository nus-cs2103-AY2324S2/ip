package duke;
import java.util.Scanner;

import duke.task.Task;



/**
 * Ui class represents the user interface for Duke.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Contains enumeration of Commands
     */
    public enum Command {
        BYE, LIST, MARK, DELETE, TODO, DEADLINE, EVENT, UNKNOWN, FIND
    }

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
    public Command getUserInput() {
        System.out.print("Enter your command: ");
        return getCommand(scanner.next());
    }

    public static Command getCommand(String input) {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
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
    public String deleteMessage(String str) {
        String res = "I have deleted the below command: \n" + str;
        System.out.println(res);
        return res;
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
    public String showGoodbyeMessage() {

        System.out.println("Bye! Hope to see you again soon.");
        return "Bye! Hope to see you again soon.";
    }

    /**
     * Displays an error message.
     *
     * @param errorMessage The error message to display.
     */
    public String showError(String errorMessage) {
        String res = "Error: " + errorMessage;
        System.out.println(res);
        return res;
    }

    /**
     * Displays an error message for loading tasks.
     */
    public String showLoadingError() {
        System.out.println("Error loading tasks from file. Starting with an empty task list.");
        return "Error loading tasks from file. Starting with an empty task list.";
    }

    /**
     * Displays a message indicating a marked task.
     *
     * @param task The task that was marked.
     */
    public String markedMessage(Task task) {
        String res = "\t"
                + "Nice! I've marked this task "
                + "as done:"
                + "\n"
                + "\t "
                + task;
        System.out.println(res);
        return res;
    }

    /**
     * Displays a message to the user.
     *
     * @param str The message to display.
     */
    public String showMessage(String str) {
        System.out.println(str);
        return str;
    }
}

