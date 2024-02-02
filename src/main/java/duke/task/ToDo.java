package duke.task;

import duke.JamieException;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description.isBlank() ? "Default Description" : description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + (getIsDone() ? "1" : "0") + " | " + getDescription();
    }
}