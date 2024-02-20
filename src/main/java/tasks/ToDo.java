package tasks;

/**
 * Represents a Task that has no starting or ending date
 */
public class ToDo extends Task {

    /**
     * Instantiates ToDo object with description.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Instantiates ToDo object with description and status.
     */
    public ToDo(String description, String status) {
        super(description, status);
    }

    /**
     * {@inheritDoc}
     * Prepends the task type of this object.
     */
    @Override
    public String toSaveFormat() {
        return "T " + super.toSaveFormat();
    }

    @Override
    public void update(String details) {
        this.description = details;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
