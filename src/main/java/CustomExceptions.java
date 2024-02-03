public class CustomExceptions extends Exception{

    public CustomExceptions(String errorMessage) {
        super(errorMessage);
    }
    public static class zeroIndexException extends CustomExceptions {
        public zeroIndexException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class toBeforeFromException extends CustomExceptions {
        public toBeforeFromException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class eventExceptionForFromTo extends CustomExceptions {
        public eventExceptionForFromTo(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class namelessTaskException extends CustomExceptions {
        public namelessTaskException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class noSuchIndexException extends CustomExceptions {
        public noSuchIndexException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class unrecognizableDateException extends CustomExceptions {
        public unrecognizableDateException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class deadlineExceptionBy extends CustomExceptions {
        public deadlineExceptionBy(String errorMessage) {
            super(errorMessage);
        }
    }
}
