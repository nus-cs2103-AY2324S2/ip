package duke.parser;

import duke.task.Task;

public class MissingInputFieldException extends InputException {

    public MissingInputFieldException(Task.TaskType type) {
        super("Missing input fields: " + type.toString().toLowerCase());
    }
}
