package eggy.exception;

/**
 * Represents an exception when saving tasks to a file.
 */
public class SaveTasksException extends EggyException {
    /**
     * Constructs a SaveTasksException.
     *
     * @param filePath The file path where the tasks are to be saved.
     */
    public SaveTasksException(String filePath) {
        super(" Error saving tasks to " + filePath + ".");
    }
}
