package henry;

/**
 * Represents an exception specific to the Henry application.
 */
public class HenryException extends Exception {
    /**
     * Constructor for the HenryException class.
     *
     * @param message The error message.
     */
    public HenryException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Whoops... " + this.getMessage();
    }
}
