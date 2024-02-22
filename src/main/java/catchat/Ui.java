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
        return "Chat with cats, it's CatChat!\n"
                + "What's up?\n"
                + "Type 'help' to see the list of commands.";
    }

    /**
     * Returns the list of available commands
     */
    public String getHelp1() {
        return "Here's a list of commands (1):\n" + getCommands1();
    }

    public String getCommands1() {
        return "(Dates are in dd/mm/yyyy format)\n"
                + "\tlist: Display task list\n"
                + "\ttodo <task>: Add a Todo\n"
                + "\tdeadline <task> /by <date>: Add a Deadline\n"
                + "\tevent <task> /from <date> /to <date>: Add an Event\n"
                + "\tmark done <index>: Mark a task as done\n"
                + "\tmark undone <index>: Mark a task as undone\n"
                + "\tdelete <index>: Delete a task\n"
                + "\tfind <keyword>: Find a keyword\n"
                + "\tbye: Exit";
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
