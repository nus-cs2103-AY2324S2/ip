package catchat;
import java.util.Scanner;

/**
 * Ui class handles the user interactions of the application
 */

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the greeting message
     */
    public String showGreeting() {
        return "Chat with cats, it's CatChat!\n" + "What's up?";
    }

    /**
     * Returns the list of available commands
     */
    public String getHelp() {
        return "Help:\n" + getCommands();
    }

    public String getCommands() {
        return "LIST - Display task list\n"
                + "TODO <TASK NAME> - Add a Todo\n"
                + "DEADLINE <TASK NAME> /by <DD/MM/YYYY> - Add a Deadline\n"
                + "EVENT <TASK NAME> /from <DD/MM/YYYY> /to <DD/MM/YYYY> - Add an Event\n"
                + "MARK DONE <INDEX> - Mark a task as done\n"
                + "MARK UNDONE <INDEX> - Mark a task as undone\n"
                + "DELETE <INDEX> - Delete a task\n"
                + "FIND <KEYWORD> - Find a keyword\n"
                + "BYE - Exit";
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
        return scanner.nextLine();
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
