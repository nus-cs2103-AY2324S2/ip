package knight;

/**
 * Represents an exception that occurs when the user inputs a non-standard command.
 */
public class NonstandardCommandException extends KnightException {
    private String errorMessage;
    public NonstandardCommandException(String message) {
        this.errorMessage = message;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
