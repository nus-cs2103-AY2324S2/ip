package chatbot.action.exception;

import chatbot.action.util.Argument;
import chatbot.action.util.Command;

/**
 * UnrecognizedArgumentException represents exceptions due to an unrecognized argument name in the action.
 *
 * @author Titus Chew
 */
public class UnrecognizedArgumentException extends ActionException {
    private final Argument argument;
    private final Command command;

    /**
     * Constructor for this ActionException for unrecognized argument names.
     *
     * @param command the command
     * @param argument the name of the argument
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
