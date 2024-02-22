package liv.task;

/**
 * Represents the task to do in a task list.
 */
public class TodoTask extends Task {

    /**
     * The constructor of the class.
     * @param description The description of the task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     * Uses [T] to denote the todo type.
     */
    @Override
    public String getDisplayedString() {
        return "[T]" + getStatusIcon() + " " + getDescription();
    }

    /**
     * {@inheritDoc}
     * Uses [T] to denote the todo type.
     */
    @Override
    public String serializeTask() {
        return "[T] | " + getStatusIcon() + " | " + getDescription();
    }
}
