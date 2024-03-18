package coat.exception;

/**
 * A custom runtime exception class for Coat-specific errors.
 *
 * <p>The {@code CoatException} class extends {@code RuntimeException} and is used to represent
 * runtime errors specific to the Coat chatbot application. It provides a way to handle and propagate
 * exceptions related to Coat's functionality.</p>
 */
public class CoatException extends RuntimeException {
    public CoatException(String message) {
        super(message);
    }
}
