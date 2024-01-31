public abstract class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public static class MissingInfoException extends DukeException {
        public MissingInfoException(String message) {
            super(message);
        }
    }

    public static class IllegalParamException extends DukeException {
        public IllegalParamException(String message) {
            super(message);
        }
    }

    public static class ParserException extends DukeException {
        public ParserException(String message) {
            super(message);
        }
    }

    public static class StorageException extends DukeException {
        public StorageException(String message) {
            super(message);
        }
    }
}

