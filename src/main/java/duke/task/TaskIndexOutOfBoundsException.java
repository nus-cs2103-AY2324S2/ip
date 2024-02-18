package duke.task;

import duke.parser.InputException;

/**
 * Represents exception thrown when specified task index is out of bounds.
 */
public class TaskIndexOutOfBoundsException extends InputException {
    TaskIndexOutOfBoundsException(int index) {
        super("duke.task.Task not found: task number " + index);
    }
}
