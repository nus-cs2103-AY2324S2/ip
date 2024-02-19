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
        return "Display your list => LIST\n"
                + "Add a Todo => todo <TASK NAME>\n"
                + "Add a Deadline => deadline <TASK NAME> /by <DD/MM/YYYY>\n"
                + "Add a Event => event <TASK NAME> /from <DD/MM/YYYY> /to <DD/MM/YYYY>\n"
                + "Mark Done => mark done <INDEX>\n"
                + "Mark Undone => mark undone <INDEX>\n"
                + "Delete a Task => delete <INDEX>\n"
                + "Find a Task => find <KEYWORD>\n"
                + "Exit => bye";
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
