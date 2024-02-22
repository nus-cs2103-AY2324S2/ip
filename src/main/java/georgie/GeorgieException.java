package georgie;

/**
 * Represents an exception specific to the Duke application.
 */
public class GeorgieException extends Exception{

    /**
     * Constructs a DukeException with the specified message.
     *
     * @param message The detail message.
     */
    public GeorgieException(String message) {
        super(message);
    }
}

