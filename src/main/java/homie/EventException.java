package homie;

/**
 * Event exception class. Handles all exceptions related to Event Class.
 * Thrown when no description of event is given, or start date or end date is of incorrect format.
 */
public class EventException extends Exception {
    /**
     * Constructor for EventException class.
     *
     * @param message The error message.
     */
    public EventException(String message) {
        super("Bruh... " + message + "\nPlease follow the format:\n event {EVENT_DESCRIPTION} "
                + "/from {dd MM yyyy HHmm} /to {dd MM yyyy HHmm}");
    }
}
