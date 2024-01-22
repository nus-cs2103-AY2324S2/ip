import java.lang.StringBuilder;

/**
 * Command is an enumeration that defines the possible ChatBot user commands.
 * A command is defined by its name and how it's arguments are used.
 *
 * @author Titus Chew
 */
public enum Command {
    /**
     * Ends the chat.
     */
    BYE(new Argument("bye")),
    /**
     * List the stored text.
     */
    LIST(new Argument("list")),
    /**
     * Mark the task as done.
     */
    MARK(new Argument("mark", "index")),
    /**
     * Mark the task as not done.
     */
    UNMARK(new Argument("unmark", "index")),
    /**
     * Adds a to-do task.
     */
    ADD_TODO(new Argument("todo", "name")),
    /**
     * Adds a deadline task.
     */
    ADD_DEADLINE(
            new Argument("deadline", "name"),
            new Argument("by", "by_date")
    ),
    /**
     * Adds an event task.
     */
    ADD_EVENT(
            new Argument("event", "name"),
            new Argument("from", "start_date"),
            new Argument("to", "end_date")
    ),
    /**
     * Invalid command.
     */
    INVALID;

    /**
     * Stores the usage hint for the command.
     */
    public final String usageHint;

    /**
     * Stores the command name.
     */
    public final String name;

    /**
     * Stores the required arguments of the command
     */
    public final Argument[] arguments;

    /**
     * Constructor for command, which parses arguments.
     * @param arguments The arguments, with a name and value.
     *                  The first argument has the name of the command.
     *                  If the value is null, there is no value for that argument.
     *                  Otherwise, the value is required.
     */
    Command(Argument... arguments) {
        // Parse the argument list
        // Name of the argument is the first argument
        this.name = arguments[0].name;
        this.arguments = arguments;
        this.usageHint = generateUsageHint(arguments);
    }

    /**
     * Constructor for un-callable command.
     */
    Command() {
        this.usageHint = null;
        this.name = null;
        this.arguments = new Argument[0];
    }

    /**
     * Generates the usage string using the arguments.
     * @param arguments The arguments of the command.
     * @return The usage hint.
     */
    private String generateUsageHint(Argument[] arguments) {
        StringBuilder usageString = new StringBuilder();
        for (int i = 0; i < arguments.length; i++) {
            if (i != 0) {
                usageString.append("/");
            }
            usageString
                    .append(arguments[i].name)
                    .append(" ");

            // null indicates that no value should be present.
            if (arguments[i].value != null) {
                usageString
                        .append("<")
                        .append(arguments[i].value)
                        .append("> ");
            }
        }
        return usageString.toString();
    }
}