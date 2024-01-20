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

    public static class InvalidCommandException extends DukeException {
        public InvalidCommandException(String message) {
            super(message);
        }
    }
}

