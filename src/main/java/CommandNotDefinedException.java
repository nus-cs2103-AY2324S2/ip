/**
 * Exception for can't recognize command,
 */
public class CommandNotDefinedException extends DukeException{

    /**
     * Constructor
     */
    public CommandNotDefinedException() {
        super();
    }

    /**
     * Print error message.
     * @return error message
     */
    @Override
    public String getMessage() {
        return String.format("%s I don't know what that means.", super.getMessage());
    }
}
