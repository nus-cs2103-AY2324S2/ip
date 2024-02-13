package duke.exceptions;
public class StorageException extends DukeException{
    public StorageException() {
        super("Error loading/saving file.");
        printStackTrace();
    }
}
