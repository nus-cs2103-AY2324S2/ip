package ally.task;

/**
 * Abstraction of Todo Tasks.
 * Child class of Task.
 */
public class Todo extends Task {

    /**
     * {@inheritDoc}
     *
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
