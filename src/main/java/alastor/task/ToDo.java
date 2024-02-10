package alastor.task;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFile() {
        return "T | " + super.toFile();
    }
}
