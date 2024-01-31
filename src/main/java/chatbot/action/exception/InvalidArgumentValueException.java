package chatbot.action.exception;

import chatbot.action.util.Argument;
import chatbot.action.util.Command;

/**
 * This represents exceptions due to an invalid argument value in an {@link Argument}.
 *
 * @author Titus Chew
 */
public final class InvalidArgumentValueException extends ActionException {
    /** Stores the associated command. */
    private final Command command;
    private final String invalidArg, reason;

    /**
     * Constructor for this {@link ActionException} with an invalid {@link Argument} value.
     *
     * @param command the associated {@link Command}
     * @param invalidArg the invalid {@link Argument}
     */
    public InvalidArgumentValueException(Command command, String invalidArg, String reason) {
        this.command = command;
        this.invalidArg = invalidArg;
        this.reason = reason;
    }

    @Override
    public String getMessage() {
        return "OOPS!!! The argument value <" + invalidArg + "> of `" + command.getName() + "` is invalid!\n"
                + "    Reason: " + reason + "\n"
                + "    Usage: `" + command.getUsageHint() + "`\n";
    }
}
