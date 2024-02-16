package raphael.task;

/**
 * The todo task class.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
    @Override
    public String toFileFormat() {
        return String.format("T |&| %s", super.toFileFormat());
    }
}
