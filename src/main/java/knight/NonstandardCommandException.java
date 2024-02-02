package knight;

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
