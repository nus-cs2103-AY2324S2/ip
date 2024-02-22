package chatbot.action.exception;

import chatbot.action.util.Command;

/**
 * This represents exceptions due to an unrecognized {@link Command} in the input.
 *
 * @author Titus Chew
 */
public final class UnrecognizedCommandException extends ActionException {
    /** Stores the name of the unrecognized {@link Command}. */
    private final String unrecognizedCommandName;

    /**
     * Constructor for this {@link ActionException} for unrecognized commands.
     */
    public UnrecognizedCommandException(String unrecognizedCommandName) {
        this.unrecognizedCommandName = unrecognizedCommandName;
    }

    @Override
    public String getMessage() {
        return "OOPS!!! I'm sorry, but I don't know what `" + unrecognizedCommandName + "` means :-(";
    }
}
