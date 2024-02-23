package chingu.exception;

/**
 * Custom NoCmdException class which return error message when there is error in the command format
 *
 * Solution are referenced from https://www.javatpoint.com/exception-class-in-java
 */

public class NoCommandException extends Exception{
    public NoCommandException(String message) {
        super(message);
    }
}
