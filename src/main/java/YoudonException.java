public class YoudonException {
    public static class EmptyDescException extends Exception {
        public EmptyDescException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class InvalidCommandException extends Exception {
        public InvalidCommandException(String errorMessage) {
            super(errorMessage);
        }
    }
}
