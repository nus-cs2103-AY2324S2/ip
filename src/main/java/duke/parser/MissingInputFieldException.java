package duke.parser;

import duke.task.Task;

/**
 * Represents exception thrown when input fields numbers do not match with requirement.
 */
public class MissingInputFieldException extends InputException {
    public MissingInputFieldException(Task.TaskType type) {
        super("Missing input fields: " + type.toString().toLowerCase());
    }
}
