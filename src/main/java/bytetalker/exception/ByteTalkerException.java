package bytetalker.exception;

public class ByteTalkerException {
    public static class TodoNoTaskException extends Exception {
        public TodoNoTaskException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class TodoUnsupportedFormatException extends Exception {
        public TodoUnsupportedFormatException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class DeadlineWrongFormatException extends Exception {
        public DeadlineWrongFormatException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class DeadlineUnsupportedFormatException extends Exception {
        public DeadlineUnsupportedFormatException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class EventWrongFormatException extends  Exception {
        public EventWrongFormatException(String errorMessages) {
            super(errorMessages);
        }
    }

    public static class UnsupportedTaskException extends Exception {
        public UnsupportedTaskException(String errorMessage) {
            super(errorMessage);
        }
    }
}
