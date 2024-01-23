package action.exception;

import action.Command;

/**
 * InvalidArgumentValueException represents exceptions due to an invalid argument value in an argument.
 *
 * @author Titus Chew
 */
public class InvalidArgumentValueException extends ActionException {
    private final Command command;
    private final String invalidArg, reason;

    /**
     * Constructor for this ActionException with an invalid argument value.
     *
     * @param command the command
     * @param invalidArg the invalid argument
     */
    public InvalidArgumentValueException(Command command, String invalidArg, String reason) {
        this.command = command;
        this.invalidArg = invalidArg;
        this.reason = reason;
    }
    @Override
    public String getMessage() {
        return "OOPS!!! The argument value <" + invalidArg + "> of " + command.name + " is invalid!\n"
                + "    Reason: " + reason + "\n"
                + "    Usage: `" + command.usageHint + "`\n";
    }
}
