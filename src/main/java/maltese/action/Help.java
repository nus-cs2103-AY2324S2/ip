package maltese.action;

/**
 * Represents an action to provide users with guidance on using the application.
 */
public class Help implements Action {

    /**
     * The content of the help message.
     */
    public static final String HELP_CONTENT = "Here are the available commands!\n"
            + "Add a todo task\n"
            + " - todo <description>\n"
            + "Add a deadline task\n"
            + " - deadline <description> /by <due date>\n"
            + "Add a event task\n"
            + " - event <description> /from <start> /to <end>\n"
            + "View all tasks\n"
            + " - list\n"
            + "Mark task(s) as done\n"
            + " - mark <indice(s)>\n"
            + "Unmark task(s) as undone\n"
            + " - unmark <indice(s)>\n"
            + "Delete task(s)\n"
            + " - delete <indice(s)>\n"
            + "Find task(s) containing the keyword\n"
            + " - find <keyword>\n"
            + "Change filepath\n"
            + " - change <filepath>\n"
            + "Exit the application\n"
            + " - bye\n";

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
    public String getResponse() {
        return HELP_CONTENT;
    }
}
