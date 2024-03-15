package exception;

/**
 * This class is an exception for when an invalid command is entered.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String s) {
        super(String.format("Invalid command %s. Please try again with todo, "
                + "deadline, event or list.", s));
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
