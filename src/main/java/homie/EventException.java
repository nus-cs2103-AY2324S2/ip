package homie;

/**
 * EventException thrown when no description of event is given, or when start date/time or end date/time is of
 * incorrect format when creating an event task.
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
