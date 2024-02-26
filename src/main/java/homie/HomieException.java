package homie;

/**
 * Homie Exception class. Handles all exceptions related to Homie class.
 * Thrown when invalid commands are given by user.
 */
public class HomieException extends Exception {
    public HomieException(String message) {
        super("Bruh... Invalid command!\nPlease try again.");
    }
}
