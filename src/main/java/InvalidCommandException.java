public class InvalidCommandException extends ChimpException {
    InvalidCommandException(String message) {
        super(message);
    }
    @Override
    public String toString() {
        return super.getMessage();
    }
}
