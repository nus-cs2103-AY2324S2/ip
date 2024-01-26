public class BobException extends Exception {
    public BobException(String message) {
        super(message);
    }
    public static class InvalidCommand extends BobException {
        public InvalidCommand(String message) {
            super(message);
        }
    }
}