package goldbot.exceptions;

public class FileCorruptionException extends GoldbotException {
    public FileCorruptionException(String errorMessage) {
        super(errorMessage);
    }
}
