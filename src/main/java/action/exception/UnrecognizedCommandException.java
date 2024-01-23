package action.exception;

/**
 * UnrecognizedCommandException represents exceptions due to an unrecognized command in the input.
 *
 * @author Titus Chew
 */
public class UnrecognizedCommandException extends ActionException {
    private final String commandName;
    /**
     * Constructor for this ActionException for unrecognized commands.
     *
     * @param commandName the name of the command
     */
    public UnrecognizedCommandException(String commandName) {
        this.commandName = commandName;
    }
    @Override
    public String getMessage() {
        return "OOPS!!! I'm sorry, but I don't know what `" + commandName + "` means :-(";
    }
}
