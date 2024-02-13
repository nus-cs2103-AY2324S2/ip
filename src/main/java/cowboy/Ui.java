package cowboy;
import java.util.Scanner;

/**
 * Ui class handles the user interactions of the application
 */

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the greeting message
     */
    public void showGreeting() {
        printLine();
        showHelp();
        printLine();
    }

    /**
     * Prints the exit message
     */
    public void showGoodbye() {
        System.out.println("\t" + "Bye. Hope to see you again soon!");
    }

    /**
     * Handles user input
     *
     * @return String user input
     */
    public String getUserInput() {
        System.out.println("Enter a task below: ");
        return scanner.nextLine();
    }

    /**
     * Close the scanner
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Prints the line
     */
    public void printLine() {
        System.out.println(LINE);
    }

    /**
     * Prints list of available commands
     */
    public void showHelp() {
        System.out.println("\t" + "Help: ");

        String[] instructions = {
            "DISPLAY LIST: list",
            "ADD TODO: todo <TASK NAME>",
            "ADD DEADLINE: deadline <TASK NAME> /by <DD/MM/YYYY>",
            "ADD EVENT: event <TASK NAME> /from <DD/MM/YYYY> /to <DD/MM/YYYY>",
            "MARK DONE: mark done <INDEX>",
            "MARK UNDONE: mark undone <INDEX>",
            "DELETE TASK: delete <INDEX>",
            "FIND TASK: find <KEYWORD>",
            "EXIT: bye"
        };
        for (int i = 0; i < instructions.length; i++) {
            System.out.println("\t" + (i + 1) + ". " + instructions[i]);
        }
    }

    /**
     * Invalid task index message
     */
    public void printInvalidTaskIndex() {
        System.out.println("\t" + "Oops, that wasn't a valid task index :P");
    }

    /**
     * Invalid keyword message
     */
    public void printInvalidKeyword() {
        System.out.println("\t" + "No tasks match that keyword :(");
    }
}
