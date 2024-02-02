package bob;

/**
 * File that defines custom exceptions for the Chat bot.
 */
public class BobException extends Exception {
    public BobException(String message) {
        super(message);
    }

    /**
     * When user enters an invalid command.
     */
    public static class InvalidCommand extends BobException {
        public InvalidCommand(String message) {
            super(message);
        }
    }

    /**
     * File I/O errors when trying to read/write to file.
     */
    public static class FileAccessError extends BobException {
        public FileAccessError(String message) {
            super(message);
        }
    }

    /**
     * Error when loading tasks from saved file.
     * Tasks may be in the wrong format.
     */
    public static class CorruptedSaveData extends BobException {
        public CorruptedSaveData(String message) {
            super(message);
        }
    }

    /**
     * When a user specified date time format is invalid.
     */
    public static class InvalidDateTimeFormat extends BobException {
        public InvalidDateTimeFormat(String message) {
            super(message);
        }
    }
}
