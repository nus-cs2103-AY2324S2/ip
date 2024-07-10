package homie;

/**
 * HomieException thrown when invalid commands are given by user.
 */
public class HomieException extends Exception {
    public HomieException() {
        super("Bruh... Invalid command!\nPlease try again.");
    }
}
