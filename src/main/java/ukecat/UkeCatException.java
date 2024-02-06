package ukecat;

/**
 * Custom exception class for UkeCat application.
 * This exception is thrown to indicate errors specific to the UkeCat application.
 * It extends the base Exception class and includes a constructor to set the error message.
 */
public class UkeCatException extends Exception {

    /**
     * Constructs a new UkeCatException with the specified error message.
     *
     * @param msg The error message that provides more information about the exception.
     */
    public UkeCatException(String msg) {
        super(msg);
    }
}
