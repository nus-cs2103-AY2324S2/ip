package knight;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task in the Knight program.
 */
public abstract class Task {
    protected boolean isDone = false;
    protected String name;
    protected static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    Task(String name) {
        this.name = name;
    }

    /**
     * Mark the task as done.
     */
    void mark() {
        this.isDone = true;
    }

    /**
     * Mark the task as not done.
     */
    void unmark() {
        this.isDone = false;
    }

    /**
     * Get the command representation of the task.
     *
     * @return The command representation of the task.
     */
    abstract String getCommand();

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + name;
    }
}
