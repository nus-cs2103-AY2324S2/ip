package duke.ui;

import java.util.Scanner;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.TaskList;

/**
 * The UI CLI class handles the displaying of UI elements in the application
 * using the command line
 *
 * @author Ryan NgWH
 */
public class Cli extends Ui {
    /**
     * Shared scanner for user input
     */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Displays the CLI UI of the application
     */
    @Override
    public void startUI(TaskList taskList) {
        // Print greeting
        printGreeting();

        boolean isExit = false;

        while (!isExit) {
            try {
                // Get user input
                String input = readCommand();
                printDividerLine();

                // Parse user input
                Command command = Parser.parse(input);

                // Execute command
                command.execute(taskList, this);

                // Exit (if applicable)
                isExit = command.isExit();
            } catch (DukeException e) {
                printError(e.getMessage());
            } finally {
                printDividerLine();
            }
        }

        sc.close();
    }

    /**
     * Print warning on standard output when application fails to load tasks from
     * file
     */
    public static void printLoadFromFileWarning() {
        System.out.println("WARNING: Failed to load from file, starting with empty list");
    }

    /**
     * Get and return the user input from standard input.
     *
     * @return User input from standard input
     */
    private String readCommand() {
        return sc.nextLine();
    }

    /**
     * Print greeting on standard output
     */
    private void printGreeting() {
        // UI Greeting
        String greeting = "Hello! I'm Ciara\n"
                + "What can I do for you?";

        // Display greeting
        printDividerLine();
        System.out.println(greeting);
        printDividerLine();
    }

    /**
     * Print divider line on standard output
     */
    private void printDividerLine() {
        System.out.println("------------------------------------------------------------");
    }

    /**
     * Print error on standard output
     *
     * @param message Error message to be printed
     */
    private void printError(String message) {
        System.out.println(String.format("ERROR: %s", message));
    }
}
