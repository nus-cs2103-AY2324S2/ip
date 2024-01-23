package action.exception;

import action.util.Command;

/**
 * MissingArgumentValueException represents exceptions due to a missing argument value for an argument in an action.
 *
 * @author Titus Chew
 */
public class MissingArgumentValueException extends ActionException {
    private final Command command;
    private final String missingArg;

    /**
     * Constructor for this ActionException with a missing argument value.
     *
     * @param command the command
     * @param missingArg the missing argument
     */
    public MissingArgumentValueException(Command command, String missingArg) {
        this.command = command;
        this.missingArg = missingArg;
    }
    @Override
    public String getMessage() {
        return "OOPS!!! The argument value <" + missingArg + "> of `" + command.name + "` must be present!\n"
                + "    Usage: `" + command.usageHint + "`";
    }
}
