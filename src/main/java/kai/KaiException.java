package kai;

/**
 * Custom exception class for Duke program.
 * Represents exceptions specific to the Duke task management program.
 *
 * <p>
 * DukeException extends the standard Java Exception class.
 * It is used to handle and propagate exceptions that may occur during the execution of the Duke program.
 * The exception contains a message describing the specific error or issue encountered.
 * </p>
 */
public class KaiException extends Exception {

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public KaiException(String message) {
        super(message);
    }
}
