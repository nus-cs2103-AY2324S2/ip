package tasks;

import exceptions.tasks.EmptyDescriptionException;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) throws EmptyDescriptionException {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) throws EmptyDescriptionException {
        super(description, isDone);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
