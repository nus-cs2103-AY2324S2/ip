package chatbot.action.exception;

import chatbot.action.Action;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;

/**
 * This represents exceptions due to an unrecognized argument name in the {@link Action}.
 *
 * @author Titus Chew
 */
public final class UnrecognizedArgumentException extends ActionException {
    /** Stores the associated {@link Argument}. */
    private final Argument argument;

    /** Stores the associated {@link Command}. */
    private final Command command;

    /**
     * Constructor for this {@link ActionException} for unrecognized {@link Argument} names.
     *
     * @param command The associated {@link Command} with that argument.
     * @param argument The {@link Argument} with the unrecognized name.
     */
    public UnrecognizedArgumentException(Command command, Argument argument) {
        this.argument = argument;
        this.command = command;
    }

    @Override
    public String getMessage() {
        return "OOPS!!! I'm sorry, but I don't know what `/" + argument.getName() + "` means :-(\n"
                + "    Usage: `" + command.getUsageHint() + "`";
    }
}
