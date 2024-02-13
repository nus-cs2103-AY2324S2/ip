package yoda.exceptions;

public class DescriptionAndTimeMissingException extends Exception {
    /**
     * Constructs a DescriptionAndTimeMissingException with a default message.
     */
    public DescriptionAndTimeMissingException() {
        super("Description and time, both missing are. Provide both, you must.");
    }
}
