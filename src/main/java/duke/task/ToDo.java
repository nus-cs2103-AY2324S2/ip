package duke.task;

import duke.JamieException;

public class ToDo extends Task {

    public ToDo(String description)  throws JamieException {
        super(description);
        if (description.isBlank()) {
            throw new JamieException("The description of a todo task cannot be empty.");
        }
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