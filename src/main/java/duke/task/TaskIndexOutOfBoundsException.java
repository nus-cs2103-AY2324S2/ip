package duke.task;

import duke.parser.InputException;

public class TaskIndexOutOfBoundsException extends InputException {

    TaskIndexOutOfBoundsException(int index) {
        super("duke.task.Task not found: task number " + index);
    }
}
