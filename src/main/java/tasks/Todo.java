package tasks;

import exceptions.tasks.EmptyDescriptionException;
import tasks.Task;

public class Todo extends Task {
    public Todo(String description) throws EmptyDescriptionException {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
