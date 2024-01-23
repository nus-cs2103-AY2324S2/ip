package action.exception;

import action.Command;

/**
 * UnrecognizedArgumentException represents exceptions due to an unrecognized argument name in the action.
 *
 * @author Titus Chew
 */
public class UnrecognizedArgumentException extends ActionException {
    private final String name;
    private final Command command;

    /**
     * Constructor for this ActionException for unrecognized argument names.
     *
     * @param name the name of the argument
     */
    public UnrecognizedArgumentException(String name, Command command) {
        this.name = name;
        this.command = command;
    }
    @Override
    public String getMessage() {
        return "OOPS!!! I'm sorry, but I don't know what `/" + name + "` means :-(\n"
                + "    Usage: `" + command.usageHint + "`";
    }
}
