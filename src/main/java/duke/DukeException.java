/*
 * DukeException.java
 * This class represents custom exceptions specific to the Duke application.
 * It extends the Exception class and is used to handle application-specific error messages.
 */

package duke;

/**
 * Represents custom exceptions specific to the Duke application.
 */
public class DukeException extends Exception {
    /**
     * Creates a new DukeException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}

