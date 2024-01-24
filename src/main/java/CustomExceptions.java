public class CustomExceptions {
    public static class zeroIndexException extends Exception {
        public zeroIndexException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class toBeforeFromException extends Exception {
        public toBeforeFromException(String errorMessage) {
            super(errorMessage);
        }
    }
}
