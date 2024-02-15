package chimp.exception;
public class InvalidCommandException extends ChimpException {
    public InvalidCommandException(String message) {
        super(message);
    }
    @Override
    public String toString() {
        return super.getMessage();
    }
}
