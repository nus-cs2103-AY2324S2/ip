package duke.DukeException;

/**
 * The DukeException class is a custom exception class in Java that extends the Exception class and allows for
 * the creation of exceptions with custom error messages.
 */
public class DukeException extends Exception{

    public DukeException(String message) {
        super(message);
    }

}
