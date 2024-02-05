package eggy.exception;

/**
 * Represents an exception when a task description is incomplete.
 */
public class IncompleteTaskException extends EggyException {
    /**
     * Constructs the exception with the task type.
     *
     * @param taskType The type of the task.
     */
    public IncompleteTaskException(String taskType) {
        super(" The description of " + (taskType.matches("^[aeiouAEIOU].*") ? "an " : "a ") + taskType
                + " cannot be empty.");
    }
}
