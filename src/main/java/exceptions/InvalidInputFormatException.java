package exceptions;

/**
 * Exception thrown when the input format for a task is invalid.
 * This can occur if the user input does not conform to the expected format for a specific type of task.
 */
public class InvalidInputFormatException extends Exception {
    /**
     * Constructs an exceptions.InvalidInputFormatException with the specified task type.
     *
     * @param taskType The type of task for which the input format is invalid.
     */
    public InvalidInputFormatException(String taskType) {
        super(String.format("Invalid format for %s task!", taskType));
    }
}
