package duke.task;

import duke.task.Task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}