package bytetalker.exception;

public class ByteTalkerException {
    public static class TodoNoTaskException extends Exception {
        public TodoNoTaskException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class DeadlineException extends Exception {
        public DeadlineException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class UnsupportedTaskException extends Exception {
        public UnsupportedTaskException(String errorMessage) {
            super(errorMessage);
        }
    }
}
