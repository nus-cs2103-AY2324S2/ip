package action;

import java.lang.StringBuilder;

/**
 * Command defines the possible unique callable names, and it's {@link Argument}(s).
 *
 * @author Titus Chew
 */
public enum Command {
    /**
     * Ends the chat.
     */
    BYE(
            new Argument("bye")
    ),
    /**
     * List the stored text.
     */
    LIST(
            new Argument("list")
    ),
    /**
     * Mark the task as done.
     */
    MARK(
            new Argument("mark", "index")
    ),
    /**
     * Mark the task as not done.
     */
    UNMARK(
            new Argument("unmark", "index")
    ),
    /**
     * Adds a to-do task.
     */
    ADD_TODO(
            new Argument("todo", "name")
    ),
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
     * Deletes a task.
     */
    DELETE(
            new Argument("delete", "index")
    );

    /**
     * Stores the usage hint for this command.
     */
    public final String usageHint;

    /**
     * Stores this command name.
     */
    public final String name;

    /**
     * Stores the arguments of this command.
     */
    public final Argument[] arguments;

    /**
     * Constructor for this command, which parses the arguments.
     * <ul>
     * <li>The first argument has the name of the command.
     * <li>If the value is null, there is no value for that argument. Otherwise, the value is required.
     *
     * @param arguments the arguments of this command
     */
    Command(Argument... arguments) {
        // Name of the argument is the first argument
        this.name = arguments[0].name;
        this.arguments = arguments;
        this.usageHint = generateUsageHint(arguments);
    }

    /**
     * Generates the usage string of this command using the arguments.
     *
     * @param arguments the arguments of this command
     * @return the usage hint
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

            if (arguments[i].value != null) {
                // not null indicates that a value should be present.
                usageString
                        .append("<")
                        .append(arguments[i].value)
                        .append("> ");
            }
        }
        usageString.deleteCharAt(usageString.length() - 1);
        return usageString.toString();
    }
}