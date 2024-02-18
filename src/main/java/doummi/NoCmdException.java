package doummi;

/**
 * Custom NoCmdException class which return error message when there is error in the command format
 */

public class NoCmdException extends Exception{
    public NoCmdException(String msg) {
        super(msg);
    }
}
