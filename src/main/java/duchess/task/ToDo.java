package duchess.task;

import duchess.task.Task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    //duchess.task.ToDo that already has isDone state
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
