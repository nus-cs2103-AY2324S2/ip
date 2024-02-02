package tasks;

import exceptions.tasks.EmptyDescriptionException;

public class Todo extends Task {
    public Todo(String description) throws EmptyDescriptionException {
        super(description);
    }

    public Todo(String description, boolean isDone) throws EmptyDescriptionException {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
