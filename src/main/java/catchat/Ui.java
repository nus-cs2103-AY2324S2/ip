package catchat;
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
     * Returns the greeting message
     */
    public String getGreeting() {
        return "Chat with cats, it's CatChat!\n" + "What's up?";
    }

    /**
     * Returns the exit message
     */
    public String getGoodbye() {
        return "Talk with me again soon!";
    }

    /**
     * Returns the list of available commands
     */
    public String getHelp() {
        return "Help: \n"
                + "DISPLAY LIST: list\n"
                + "ADD TODO: todo <TASK NAME>\n"
                + "ADD DEADLINE: deadline <TASK NAME> /by <DD/MM/YYYY>\n"
                + "ADD EVENT: event <TASK NAME> /from <DD/MM/YYYY> /to <DD/MM/YYYY>\n"
                + "MARK DONE: mark done <INDEX>\n"
                + "MARK UNDONE: mark undone <INDEX>\n"
                + "DELETE TASK: delete <INDEX>\n"
                + "FIND TASK: find <KEYWORD>\n"
                + "EXIT: bye";
    }

    /**
     * Returns the message for an invalid task index
     */
    public String getInvalidTaskIndex() {
        return "Oops, that wasn't a valid task index :P";
    }

    /**
     * Returns the message for an invalid keyword
     */
    public String getInvalidKeyword() {
        return "No tasks match that keyword :(";
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
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Handles user input
     *
     * @return String user input
     */
    public String getUserInput() {
        // System.out.println("Enter a task below: ");
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
    public String printLine() {
        return LINE;
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
    public String printInvalidTaskIndex() {
        return "Oops, that wasn't a valid task index :P";
    }

    /**
     * Invalid keyword message
     */
    public String printInvalidKeyword() {
        return "No tasks match that keyword :(";
    }
}
