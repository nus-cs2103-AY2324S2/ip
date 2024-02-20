package chingu.exception;

/**
 * Custom DateException class which return error message when there is error in the date format
 */

public class DateException extends Exception {
    public DateException(String msg) {
        super(msg);
    }
}
