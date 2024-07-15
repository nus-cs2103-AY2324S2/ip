package knight;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task in the Knight program.
 */
public abstract class Task {
    protected boolean isDone = false;
    protected String name;
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
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
    abstract void update(String updateMessage);

    /**
     * Checks if the task's name contains the keyword.
     * @param keyword The keyword to search for.
     * @return True if the task's name contains the keyword, false otherwise.
     */
    boolean matches(String keyword) {
        return name.contains(keyword);
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + name;
    }
}
