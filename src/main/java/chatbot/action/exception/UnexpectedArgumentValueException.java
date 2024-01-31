package chatbot.action.exception;

import chatbot.action.util.Argument;
import chatbot.action.util.Command;

/**
 * This represents exceptions due to the presence of a value
 * in an {@link Argument} that does not require a value.
 *
 * @author Titus Chew
 */
public final class UnexpectedArgumentValueException extends ActionException {
    /** Stores the associated {@link Argument}. */
    private final Argument argument;

    /** Stores the associated {@link Command}. */
    private final Command command;

    /**
     * Constructor for this {@link ActionException} for unrecognized {@link Argument} names.
     *
     * @param command the associated {@link Command}
     * @param argument the {@link Argument}
     */
    public UnexpectedArgumentValueException(Command command, Argument argument) {
        this.argument = argument;
        this.command = command;
    }

    @Override
    public String getMessage() {
        return "OOPS!!! I'm sorry, but I didn't expect <" + argument.getValue() + "> :-(\n"
                + "    Usage: `" + command.getUsageHint() + "`";
    }
}
