package eggy.exception;

public class SaveTasksException extends EggyException {
    public SaveTasksException(String filePath) {
        super("Error saving tasks to " + filePath + ".");
    }
}
