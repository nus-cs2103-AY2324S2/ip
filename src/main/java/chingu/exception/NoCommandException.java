package chingu.exception;

/**
 * Custom NoCmdException class which return error message when there is error in the command format
 */

public class NoCommandException extends Exception{
    public NoCommandException(String msg) {
        super(msg);
    }
}
