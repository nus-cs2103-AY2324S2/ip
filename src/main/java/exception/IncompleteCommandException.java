package exception;

/**
 * This class is an exception for incomplete commands.
 */
public class IncompleteCommandException extends Exception {

    public IncompleteCommandException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }

}
