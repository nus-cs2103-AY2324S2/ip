package exceptions;

/**
 * Custom exception class for ChillChief application.
 * Represents exceptions specific to ChillChief application.
 */
public class ChillChiefException extends Exception {

    /**
     * Constructs a ChillChiefException with a custom error message.
     * @param errorMessage The error message describing the exception.
     */
    public ChillChiefException(String errorMessage) {
        super(errorMessage);
    }
}
