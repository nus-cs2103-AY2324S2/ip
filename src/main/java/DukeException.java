/**
 * Represents a generic exception for our Duke bot.
 * <p>
 * This class acts as a base for all specific Exception types.
 * It can be extended to create the corresponding Exception.
 */
public class DukeException extends Exception {

    /**
     * Creates a Exception object.
     * Will call the super constructor from the Java Exception object.
     *
     * @param message The error message of the Exception.
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "\n______________________________________";
    }
}
