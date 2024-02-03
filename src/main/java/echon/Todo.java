package echon;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    /**
     * Creates a todo task.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFileLine() {
        String fileLine = super.toFileLine();
        return String.format("T | %s", fileLine.substring(4));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
