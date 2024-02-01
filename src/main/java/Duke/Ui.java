package duke;
import java.util.Scanner;

/**
 * This class represents the user interface of the application.
 * It handles user interactions and commands.
 */
public class Ui {

    /**
     * Constructs a new Ui.
     */
    public Ui() {

    }

    /**
     * Starts the user interface.
     * Greets the user and starts scanning for user input.
     */
    public void run() {
        greet();
        scan();
    }

    /**
     * Prints a greeting message to the console.
     */
    public void greet() {
        System.out.println(" /\\_/\\");
        System.out.println("((@v@))");
        System.out.println("():::()");
        System.out.println(" VV-VV");
        System.out.println("What can I do for you?");
    }

    /**
     * Scans for and handles user input.
     * Continues scanning until the user enters a command to exit.
     */
    public void scan() {
        CommandHandler commandHandler = new CommandHandler(this);
        Scanner scanner = new Scanner(System.in);

        boolean exitScan = false;
        while (!exitScan) {
            String userInput = scanner.nextLine();
            try {
                exitScan = commandHandler.executeCommand(userInput);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * Prints a goodbye message to the console.
     */
    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}