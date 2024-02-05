package tasklist.tasks;

/** Custom exception to indicate that the date param is empty. */
public class EmptyDateException extends RuntimeException {

    /** Constructs the exception with a message about the error. */
    public EmptyDateException() {
        super("Please enter a date.");
    }
}
