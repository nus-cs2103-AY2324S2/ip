package duke.exception;

/**
 * Exception thrown when the file to save task list data cannot be found
 */
public class FileNotFoundException extends Exception {
    public FileNotFoundException() {
        super("Please create new save file!");
    }
}
