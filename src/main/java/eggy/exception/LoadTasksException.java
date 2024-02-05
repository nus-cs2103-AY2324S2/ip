package eggy.exception;

public class LoadTasksException extends EggyException {
    public LoadTasksException(String filePath) {
        super(" Error loading tasks from " + filePath + ".");
    }
}
