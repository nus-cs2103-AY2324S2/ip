package duke.exceptions;

/**
 * The InvalidTaskException class represents an exception indicating an invalid task.
 * <p>
 * This exception is thrown when the format or content of a task is invalid.
 * </p>
 * <p>
 * It extends the DukeException class.
 * </p>
 *
 * @author Your Name
 * @version 1.0
 * @see duke.exceptions.DukeException
 */
public class InvalidTaskException extends DukeException{

    /**
     * Constructs a new InvalidTaskException with a default message.
     */
    public InvalidTaskException() {
        super("Invalid task. Use help for guidance.");
    }
}
