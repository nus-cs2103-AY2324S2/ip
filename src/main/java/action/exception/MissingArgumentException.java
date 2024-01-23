package action.exception;

import action.Command;

/**
 * MissingArgumentException represents exceptions due to a missing argument in an action.
 *
 * @author Titus Chew
 */
public class MissingArgumentException extends ActionException {
    private final Command command;
    private final String missingArg;

    /**
     * Constructor for this ActionException with a missing argument.
     *
     * @param command the command
     * @param missingArg the missing argument
     */
    public MissingArgumentException(Command command, String missingArg) {
        this.command = command;
        this.missingArg = missingArg;
    }
    @Override
    public String getMessage() {
        return "OOPS!!! The argument `/" + missingArg + "` of `" + command.name + "` must be present!\n"
                + "    Usage: `" + command.usageHint + "`";
    }
}
