package duke.application;

public class DukeException extends IllegalArgumentException {

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Constructs a DukeException with the specified error message and a nested exception.
     *
     * @param message The error message associated with the exception.
     * @param e       The nested exception that caused this DukeException.
     */
    public DukeException(String message, Exception e) {
        super(message, e);
    }

    /**
     * Creates a DukeException for a generic loading error without a nested exception.
     *
     * @return A DukeException indicating an error during the loading process.
     */
    public static DukeException createLoadingError() {
        return new DukeException("Error loading files");
    }

    /**
     * Creates a DukeException for a loading error with a nested exception.
     *
     * @param e The nested exception that caused the loading error.
     * @return A DukeException indicating an error during the loading process with details from the nested exception.
     */
    public static DukeException showLoad(Exception e) {
        return new DukeException("Error loading files", e);
    }
}
