package emis;

import java.util.Scanner;

/**
 * The Ui class deals with interactions with the user in the EMIS application.
 */
public class Ui {
    /** The scanner object for reading user input. */
    private static Scanner scanner;

    /**
     * Constructs a new Ui object and initializes the scanner for reading user input.
     */
    public Ui () {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println("\tHello! I'm Emis!\n \tWhat can I do for you?");
        showLine();
    }

    /**
     * Displays a line separator.
     */
    public static void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Reads the user command from the console.
     * 
     * @return The user command as a string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays an error message indicating a loading error.
     */
    public void showLoadingError() {
        showLine();
        System.out.println("\t There was an error loading the file.");
        showLine();
    }

    /**
     * Displays an error message.
     * 
     * @param errorMessage The error message to display.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Displays a help message providing instructions on how to use the application.
     */
    public void showHelp() {
        showLine();
        System.out.println("\tEmis is happy to help with printing a list of tasks with the command 'list'.");
        System.out.println("\tAdd todos with the command 'todo (insert task here)'.");
        System.out.println("\tAdd deadlines with the command 'deadline (insert deadline name) /by (insert deadline here in the format yyyy-mm-dd hhmm)'.");
        System.out.println("\tAdd events with the command 'event (insert event name) /from (insert start time) /to (insert end time)'.");
        System.out.println("\tMark tasks as done with the command 'mark (task no)'.");
        System.out.println("\tMark tasks as undone with the command 'unmark (task no)'.");
        System.out.println("\tDelete tasks  with the command 'delete (task no)'.");
        System.out.println("\tTo stop talking to Emis, please say 'bye'.");
        showLine();
    }

    /**
     * Closes the scanner and exits the application.
     */
    public static void exit() {
        scanner.close();
        showLine();
        System.out.println("\tBye. Hope to see you again soon!");
        showLine();
        System.exit(0);
    }
} 