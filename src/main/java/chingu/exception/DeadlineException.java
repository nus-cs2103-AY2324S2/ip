package chingu.exception;

/**
 * Custom DeadlineException class which return error message when there is error in the deadliine format
 *
 * Solution are referenced from https://www.javatpoint.com/exception-class-in-java
 */

public class DeadlineException extends Exception{
    public DeadlineException(String message) {
        super(message);
    }
}
