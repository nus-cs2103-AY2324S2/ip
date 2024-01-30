package duke.task;

import duke.task.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) { super(description, isDone); }

    @Override
    public String outputString() {
        return "T | " + super.outputString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
