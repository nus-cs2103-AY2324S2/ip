package duke;
/**
 * DukeException is an exception class for handling exceptions.
 * It extends the general Exception class.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
        public DukeException(String message) {
            super(message);
        }
    }

