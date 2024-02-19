package duke.exceptions;

public class FileCorruptionException extends DukeException {
    public FileCorruptionException(String errorMessage) {
        super(errorMessage);
    }
}
