public class BobException extends Exception {
    public BobException(String message) {
        super(message);
    }

    public static class InvalidCommand extends BobException {
        public InvalidCommand(String message) {
            super(message);
        }
    }

    public static class FileAccessError extends BobException {
        public FileAccessError(String message) {
            super(message);
        }
    }

    public static class CorruptedSaveData extends BobException {
        public CorruptedSaveData(String message) {
            super(message);
        }
    }

    public static class InvalidDateTimeFormat extends BobException {
        public InvalidDateTimeFormat(String message) {
            super(message);
        }
    }
}
