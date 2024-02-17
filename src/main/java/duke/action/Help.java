package duke.action;

/**
 * Represents an action to provide users with guidance on using the application.
 */
public class Help implements Action {

    /**
     * The content of the help message.
     */
    public static final String HELP_CONTENT = "Here is the guide!\n"
            + "Available commands:\n"
            + "- todo <description>: Add a new todo task\n"
            + "- deadline <description> /by <due date>: Add a new deadline task\n"
            + "- event <description> /from <start date> /to <end date>: Add a new event task\n"
            + "- list: View all tasks\n"
            + "- mark <task indices>: Mark tasks as done\n"
            + "- unmark <task indices>: Unmark tasks as undone\n"
            + "- delete <task indices>: Delete tasks\n"
            + "- find <keyword>: Find tasks containing the keyword\n"
            + "- bye: Exit the application\n"
            + "- help: Show this help message\n";

    /**
     * Constructs a Help action.
     * This action does not require any parameters.
     */
    public Help() {
        // No constructor logic needed as the help message is static.
    }

    /**
     * Parses the Help command and creates a Help action.
     * @return A Help action.
     */

    public static Help parse() {
        return new Help();
    }

    /**
     * Retrieves the help message.
     *
     * @return A string representing the help message.
     */
    @Override
    public String response() {
        return HELP_CONTENT;
    }
}
