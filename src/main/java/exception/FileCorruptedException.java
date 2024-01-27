package exception;

public class FileCorruptedException extends NarutoException {
    public FileCorruptedException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.FILE_CORRUPTED;
    }
}
