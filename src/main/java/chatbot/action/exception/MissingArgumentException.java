package chatbot.action.exception;

import chatbot.action.Action;
import chatbot.action.util.Argument;
import chatbot.action.util.Command;

/**
 * This represents exceptions due to a missing {@link Argument} in an {@link Action}.
 *
 * @author Titus Chew
 */
public final class MissingArgumentException extends ActionException {
    /** Stores the associated command. */
    private final Command command;
    private final Argument missingArg;

    /**
     * Constructor for this {@link ActionException} with a missing {@link Argument}.
     *
     * @param command The associated {@link Command} with the argument.
     * @param missingArg The missing {@link Argument}.
     */
    public MissingArgumentException(Command command, Argument missingArg) {
        this.command = command;
        this.missingArg = missingArg;
    }

    @Override
    public String getMessage() {
        return "OOPS!!! The argument `/" + missingArg.getName() + "` of `" + command.getName() + "` must be present!\n"
                + "    Usage: `" + command.getUsageHint() + "`";
    }
}
