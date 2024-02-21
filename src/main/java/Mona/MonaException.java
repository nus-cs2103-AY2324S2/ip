package mona;

/**
 * This class represents exceptions that can be thrown by Mona during runtime
 */
public class MonaException extends Exception {
    public MonaException(String errorMessage) {
        super(errorMessage);
    }
}
