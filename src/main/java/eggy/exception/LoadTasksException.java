package eggy.exception;

/**
 * Represents an exception when loading tasks from a file.
 */
public class LoadTasksException extends EggyException {
    /**
     * Constructs a LoadTasksException.
     *
     * @param filePath The file path where the tasks are loaded from.
     */
    public LoadTasksException(String filePath) {
        super(" Error loading tasks from " + filePath + ".");
    }
}
