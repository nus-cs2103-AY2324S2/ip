package chingu.exception;

/**
 * Custom ToDosException class which return error message when there is error in the Todos format
 *
 * Solution are referenced from https://www.javatpoint.com/exception-class-in-java
 */

public class ToDosException extends Exception{
    public ToDosException(String message) {
        super(message);
    }
}
