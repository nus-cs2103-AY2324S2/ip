package action.exception;

import action.util.Argument;
import action.util.Command;

/**
 * MissingArgumentException represents exceptions due to a missing argument in an action.
 *
 * @author Titus Chew
 */
public class MissingArgumentException extends ActionException {
    private final Command command;
    private final Argument missingArg;

    /**
     * Constructor for this ActionException with a missing argument.
     *
     * @param command the command
     * @param missingArg the missing argument
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
