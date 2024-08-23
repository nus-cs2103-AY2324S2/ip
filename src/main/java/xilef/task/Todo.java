package xilef.task;

/**
 * A {@code Todo} is a type of {@code Task} with no associated date or time.
 */
public class Todo extends Task {
    /**
     * Creates a new {@code Todo} with the given description.
     *
     * @param description The description of the {@code Todo}.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString().trim();
    }

    @Override
    public String toStringForFile() {
        return "T | " + super.toStringForFile();
    }
}
