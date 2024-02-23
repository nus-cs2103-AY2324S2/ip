package lindi.task;

/**
 * Represents an todo task
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toParsedFormat() {
        return String.format("T | %c | %s",
                this.isDone ? 'y' : 'n', this.description);
    }
}
