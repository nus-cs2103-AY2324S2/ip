package chingu.exception;

/**
 * Custom ToDosException class which return error message when there is error in the Todos format
 */

public class ToDosException extends Exception{
    public ToDosException(String msg) {
        super(msg);
    }
}
