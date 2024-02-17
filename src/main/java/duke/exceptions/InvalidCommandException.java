package duke.exceptions;

/**
 * The InvalidCommandException class represents an exception indicating an invalid command.
 * <p>
 * This exception is thrown when the user inputs an invalid command.
 * </p>
 * <p>
 * It extends the DukeException class.
 * </p>
 *
 * @author Justin Leng Chern Harn
 * @version 1.0
 * @see duke.exceptions.DukeException
 */
public class InvalidCommandException extends DukeException {

    /**
     * Constructs a new InvalidCommandException with a default message.
     */
    public InvalidCommandException() {
        super("Invalid command. Use help to for more info");
    }
}
