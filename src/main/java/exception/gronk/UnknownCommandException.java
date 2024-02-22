package exception.gronk;

/**
 * UnknownCommandException class.
 * Exception when a command is not recognized by the Parser class.
 */
public class UnknownCommandException extends Exception {
    public UnknownCommandException() {}

    public UnknownCommandException(String m) {
        super(m);
    }

    @Override
    public String toString() {
        return "I'm sorry, I didn't recognize that command. Please try again!";
    }
}
