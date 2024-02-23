package chingu.exception;

/**
 * Custom EventException class which return error message when there is error in the events format
 *
 * Solution are referenced from https://www.javatpoint.com/exception-class-in-java
 */

public class EventException extends Exception{
    public EventException(String message) {
        super(message);
    }
}
