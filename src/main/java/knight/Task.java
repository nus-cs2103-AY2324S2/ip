package knight;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected boolean isDone = false;
    protected String name;

    protected static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    Task(String name) {
        this.name = name;
    }

    void mark() {
        this.isDone = true;
    }

    void unmark() {
        this.isDone = false;
    }

    abstract String getCommand();

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
