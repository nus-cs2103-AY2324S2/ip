package chingu.exception;

/**
 * Custom DateException class which return error message when there is error in the date format
 *
 * Solution are referenced from https://www.javatpoint.com/exception-class-in-java
 */

public class DateException extends Exception {
    public DateException(String message) {
        super(message);
    }
}
