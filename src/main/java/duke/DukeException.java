package duke;

/**
 * The DukeException class represents exceptions specific to the Duke application.
 * It extends the Exception class and provides constructors for creating Duke-related exceptions.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
