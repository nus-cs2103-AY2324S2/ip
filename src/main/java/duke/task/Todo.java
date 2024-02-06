package duke.task;

import duke.task.Task;

public class Todo extends Task {
    public Todo(String description, boolean isComplete) {
        super(description);
        this.isComplete = isComplete;
    }

    @Override
    public String toFileFormat() {
        return String.format("T | %d | %s", isComplete ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "T | " + (isComplete ? 1 : 0) + " | " + description;
    }


}
