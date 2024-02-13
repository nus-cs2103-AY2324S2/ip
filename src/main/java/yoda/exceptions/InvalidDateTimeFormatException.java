package yoda.exceptions;

public class InvalidDateTimeFormatException extends Exception{

    /**
     * Constructs an InvalidDateTimeFormatException with a default message.
     */
    public InvalidDateTimeFormatException() {
        super("Invalid, the date format is. Use one of the accepted formats, you must.");
    }
}
