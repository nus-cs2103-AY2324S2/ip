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

    static String getWelcomeMessage(){
        return "📝 Welcome to Charmbot Task Manager! 📝\n\n"
                + "Hello! I'm Charmbot, your personal task manager. I'm here to help you stay organized and on top of your tasks. Whether you need to add a new task, mark one as complete, or simply review your schedule, I've got you covered!\n\n"
                + "Feel free to explore my features and start managing your tasks like a pro. If you ever need assistance, just let me know. Let's make managing tasks a breeze! 💼";
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