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

    /** Stores the invalid argument. */
    private final String invalidArg;

    /** Stores the reason for the invalid value. */
    private final String reason;

    /**
     * Constructor for this {@link ActionException} with an invalid {@link Argument} value.
     *
     * @param command The associated {@link Command} with that argument.
     * @param invalidArg The {@link Argument} with the invalid value.
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
