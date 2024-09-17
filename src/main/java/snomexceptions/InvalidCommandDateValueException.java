package snomexceptions;

/**
 * This is the exception thrown when the user keys in a start date
 * that is after the end date for a task of type Event.
 */
public class InvalidCommandDateValueException extends InvalidCommandException {


    public InvalidCommandDateValueException() {
        super("Please make sure that your end date is after the start date");
    }
}
