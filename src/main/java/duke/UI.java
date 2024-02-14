package duke;
import java.util.Scanner;

/**
 * The Ui class handles the user interface of the Duke application.
 */
public class Ui {
    /**
     * The logo of the Duke application.
     */
    public static final String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints the Duke logo.
     */
    static void printLogo() {
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints the welcome message when the Duke application starts.
     */
    static void printWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm CharmBot ");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the goodbye message when the Duke application exits.
     */
    static void printGoodbyeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
    
    /**
     * Retrieves the user command from the scanner.
     *
     * @param scanner The scanner used to read user input.
     * @return The user command.
     */
    static String getUserCommand(Scanner scanner) {
        return scanner.nextLine();
    }

    

    /**
     * Prints a message to the user.
     *
     * @param message The message to be printed.
     */
    static void printMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}