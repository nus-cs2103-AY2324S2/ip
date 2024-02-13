package duke.exceptions;
<<<<<<< HEAD

public class StorageException extends DukeException {
    public StorageException() {
        super("Error loading/saving file.");
=======
public class StorageException extends DukeException{
    public StorageException() {
        super("Error loading/saving file.");
        printStackTrace();
>>>>>>> branch-A-Packages
    }
}
