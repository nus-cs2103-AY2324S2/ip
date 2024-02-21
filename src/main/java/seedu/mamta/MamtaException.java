package seedu.mamta;

/**
 * Custom exception class for Mamta task manager application.
 * Used to handle various types of exceptions.
 */
public class MamtaException {

    /**
     * The exception message.
     */
    private final String exceptionMessage;

    /**
     * Constructs a MamtaException object with the given exception message.
     * @param exceptionMessage The exception message.
     */
    MamtaException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    /**
     * Returns a MamtaException object for an invalid task type.
     * @return A MamtaException object for an invalid task type.
     */
    public static MamtaException invalidTaskType() {
        return new MamtaException("Na manega! You entered an invalid task type. Sorry!");
    }

    public static MamtaException UiError() {
        return new MamtaException("Sharam toh aa nahi raha hain! Error Loading UI.");
    }

    public static MamtaException commandFormatError()
    {
        return new MamtaException("KAISE? Your command format is invalid please re enter!");
    }
    /**
     * Returns a MamtaException object for an incomplete task description.
     * @return A MamtaException object for an incomplete task description.
     */
    public static MamtaException incompleteTaskDescription() {
        return new MamtaException("Abe, I don't know what to do! You did not give me instructions!");
    }

    /**
     * Returns a MamtaException object for invalid dates.
     * @return A MamtaException object for invalid dates.
     */
    public static MamtaException invalidDates() {
        return new MamtaException("Oye, dates enter properly man!");
    }

    /**
     * Returns a MamtaException object for an IOException.
     * @return A MamtaException object for an IOException.
     */
    public static MamtaException IOException() {
        return new MamtaException("Guddhi laal kardunga, error with input files!");
    }

    /**
     * Returns a string representation of the MamtaException object.
     * @return A string representation of the MamtaException object.
     */
    public String toString() {
        return String.format("You ran into this error: %s", this.exceptionMessage);
    }
}
