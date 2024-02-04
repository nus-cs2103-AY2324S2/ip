package duke;

public class CustomExceptions extends Exception {

    public CustomExceptions(String errorMessage) {
        super(errorMessage);
    }
    public static class ZeroIndexException extends CustomExceptions {
        public ZeroIndexException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class InvalidTaskException extends CustomExceptions {
        public InvalidTaskException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class UnrecognizedCommandException extends CustomExceptions {
        public UnrecognizedCommandException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class ToBeforeFromException extends CustomExceptions {
        public ToBeforeFromException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class EventExceptionForFromTo extends CustomExceptions {
        public EventExceptionForFromTo(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class NamelessTaskException extends CustomExceptions {
        public NamelessTaskException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class NoSuchIndexException extends CustomExceptions {
        public NoSuchIndexException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class UnrecognizableDateException extends CustomExceptions {
        public UnrecognizableDateException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class DeadlineExceptionBy extends CustomExceptions {
        public DeadlineExceptionBy(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class MarkException extends CustomExceptions {
        public MarkException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class FindException extends CustomExceptions {
        public FindException(String errorMessage) {
            super(errorMessage);
        }
    }
}
