package duke.task;

/**
 * Subclass of task that represents ToDo tasks.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo object.
     *
     * @param description Description of task.
     * @param isDone Boolean value describing completion status
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * @inheritDoc
     *
     * @return description + isDone + from + to
     */
    @Override
    public String getTokens() {
        return String.join(",", TaskType.TODO.toString(), super.getTokens());
    }
}
