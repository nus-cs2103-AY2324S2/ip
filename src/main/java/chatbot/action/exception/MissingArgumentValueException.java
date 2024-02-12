package chatbot.action.exception;

import chatbot.action.Action;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;

/**
 * This represents exceptions due to a missing argument value
 * for an {@link Argument} in an {@link Action}.
 *
 * @author Titus Chew
 */
public final class MissingArgumentValueException extends ActionException {
    /** Stores the associated command. */
    private final Command command;
    private final Argument missingArg;

    /**
     * Constructor for this {@link ActionException} with a missing {@link Argument} value.
     *
     * @param command The associated {@link Command} with that argument.
     * @param missingArg The {@link Argument} with the missing value.
     */
    public MissingArgumentValueException(Command command, Argument missingArg) {
        this.command = command;
        this.missingArg = missingArg;
    }

    @Override
    public String getMessage() {
        return "OOPS!!! The argument value <" + missingArg.getValue() + "> of `"
                + command.getName() + "` must be present!\n"
                + "    Usage: `" + command.getUsageHint() + "`";
    }
}
