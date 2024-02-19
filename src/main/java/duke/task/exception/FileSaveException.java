package duke.task.exception;

public class FileSaveException extends Exception {

    public FileSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public static class SaveError extends FileSaveException {
        public SaveError(Throwable cause) {
            super("Error saving tasks to your file", cause);
        }
    }
}
