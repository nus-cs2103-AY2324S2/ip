package wis.task;

/**
 * An simple todo task that has no additional information. A
 * description of the task is inherited from Task class.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSavedString() {
        return "T#!#" + super.toSavedString() + "\n";
    }
}
