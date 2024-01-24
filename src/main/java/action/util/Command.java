package action.util;

import action.exception.ActionException;
import action.exception.MissingArgumentException;

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
            new ExpectedArgument("bye")
    ),
    /**
     * List the stored text.
     */
    LIST(
            new ExpectedArgument("list")
    ),
    /**
     * Mark the task as done.
     */
    MARK(
            new ExpectedArgument("mark", "index")
    ),
    /**
     * Mark the task as not done.
     */
    UNMARK(
            new ExpectedArgument("unmark", "index")
    ),
    /**
     * Adds a to-do task.
     */
    ADD_TODO(
            new ExpectedArgument("todo", "name")
    ),
    /**
     * Adds a deadline task.
     */
    ADD_DEADLINE(
            new ExpectedArgument("deadline", "name"),
            new ExpectedArgument("by", "by_date")
    ),
    /**
     * Adds an event task.
     */
    ADD_EVENT(
            new ExpectedArgument("event", "name"),
            new ExpectedArgument("from", "start_date"),
            new ExpectedArgument("to", "end_date")
    ),
    /**
     * Deletes a task.
     */
    DELETE(
            new ExpectedArgument("delete", "index")
    );

    /**
     * Stores the usage hint for this command.
     */
    private final String usageHint;

    /**
     * Stores this command name.
     */
    private final String name;

    /**
     * Stores the expected arguments of this command.
     */
    private final ExpectedArgument[] arguments;

    /**
     * Constructor for this command, which parses the expected arguments.
     * <ul>
     * <li>The first argument has the name of the command.
     * <li>If the value is null, there is no value for that argument. Otherwise, the value is required.
     *
     * @param arguments the arguments of this command
     */
    Command(ExpectedArgument... arguments) {
        // Name of the argument is the first argument
        this.name = arguments[0].getName();
        this.arguments = arguments;
        this.usageHint = generateUsageHint(arguments);
    }

    /**
     * Generates the usage string of this command using the arguments.
     *
     * @param arguments the expected arguments of this command
     * @return the usage hint
     */
    private String generateUsageHint(ExpectedArgument[] arguments) {
        StringBuilder usageString = new StringBuilder();
        for (int i = 0; i < arguments.length; i++) {
            if (i != 0) {
                usageString.append("/");
            }
            usageString
                    .append(arguments[i].getName())
                    .append(" ");

            if (arguments[i].getValue() != null) {
                // not null indicates that a value should be present.
                usageString
                        .append("<")
                        .append(arguments[i].getValue())
                        .append("> ");
            }
        }
        usageString.deleteCharAt(usageString.length() - 1);
        return usageString.toString();
    }

    /**
     * Checks if the command expects an argument name.
     *
     * @param otherArgument the argument with the name to find
     * @return true if the command has that argument name, otherwise false
     */
    public boolean hasArgumentName(Argument otherArgument) {
        for (Argument arg : arguments) {
            if (arg.hasSameArgumentName(otherArgument)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the command usage hint.
     *
     * @return the usage hint
     */
    public String getUsageHint() {
        return usageHint;
    }

    /**
     * Gets the name of the command.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Validates the supplied argument names and values
     *
     * @param suppliedArguments the argument names
     * @throws ActionException If an argument is missing.
     */
    public void validateSuppliedArguments(Argument[] suppliedArguments) throws ActionException {
        for (ExpectedArgument expectedArg : this.arguments) {
            boolean isRecognized = false;
            for (Argument suppliedArg : suppliedArguments) {
                if (expectedArg.hasSameArgumentName(suppliedArg)) {
                    expectedArg.validateArgument(this, suppliedArg);
                    isRecognized = true;
                    break;
                }
            }

            if (!isRecognized) {
                throw new MissingArgumentException(this, expectedArg);
            }
        }
    }
}