package action.exception;

/**
 * UnrecognizedCommandException represents exceptions due to an unrecognized command in the input.
 *
 * @author Titus Chew
 */
public class UnrecognizedCommandException extends ActionException {
    private final String name;
    /**
     * Constructor for this ActionException for unrecognized commands.
     *
     * @param name the name of the command
     */
    public UnrecognizedCommandException(String name) {
        this.name = name;
    }
    @Override
    public String getMessage() {
        return "OOPS!!! I'm sorry, but I don't know what `" + name + "` means :-(";
    }
}
