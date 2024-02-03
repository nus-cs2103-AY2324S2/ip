package toothless;

/**
 * Represents an exception specific to the Toothless application.
 * This class is used to handle exceptions unique to the application's logic and operations,
 * providing more specific context to the user for error handling within the Toothless application.
 */
public class ToothlessException extends Exception {

    /**
     * Constructs a new ToothlessException with the specified detail message.
     * The details are retrievable using the getMessage() method.
     * @param message The detail message.
     */
    public ToothlessException(String message) {
        super(message);
    }
}
