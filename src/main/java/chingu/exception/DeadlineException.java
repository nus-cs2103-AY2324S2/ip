package chingu.exception;

/**
 * Custom DeadlineException class which return error message when there is error in the deadliine format
 */

public class DeadlineException extends Exception{
    public DeadlineException(String msg) {
        super(msg);
    }
}
