package action.exception;

import action.util.Argument;
import action.util.Command;

/**
 * UnexpectedArgumentValueException represents exceptions due to the presence of a value
 * in an argument that does not require a value.
 *
 * @author Titus Chew
 */
public class UnexpectedArgumentValueException extends ActionException {
    private final Argument argument;
    private final Command command;

    /**
     * Constructor for this ActionException for unrecognized argument names.
     *
     * @param command the command
     * @param argument the argument
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
