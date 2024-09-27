package yoda.exceptions;

public class InvalidDateTimeFormatException extends Exception{

    /**
     * Constructs an InvalidDateTimeFormatException with a default message.
     */
    public InvalidDateTimeFormatException() {
        super("Invalid, the date format is. Use one of the accepted formats, you must.");
    }

    /**
     * Constructs an InvalidDateTimeFormatException with the specified message.
     *
     * @param message The message to be included in the exception.
     */
    public InvalidDateTimeFormatException(String message) {
        super(message);
    }
}
