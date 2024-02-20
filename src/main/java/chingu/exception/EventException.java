package chingu.exception;

/**
 * Custom EventException class which return error message when there is error in the events format
 */

public class EventException extends Exception{
    public EventException(String msg) {
        super(msg);
    }
}
