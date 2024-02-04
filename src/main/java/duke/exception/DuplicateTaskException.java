package duke.exception;

import duke.task.Task;

public class DuplicateTaskException extends DukeException {
    Task task;
    public DuplicateTaskException(Task task) {
        super();
        this.task=task;
    }

    @Override
    public String getMessage() {
        return String.format("%s the task\n    %s\nis duplicated.", super.getMessage(), this.task);
    }
}
