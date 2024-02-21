package task;

import java.time.format.DateTimeFormatter;

public abstract class Task {
    private String name;
    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected boolean isDone;

    Task(String name) {
        this(name, false);
    }

    Task(String name, boolean done) {
        this.name = name;
        this.isDone = done;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    protected String getDescription() {
        return this.name;
    }

    /**
     * Converts a task into the text representation in a .txt file.
     * @return the text representation.
     */
    public abstract String fileRepresentation();

    public boolean hasSubstring(String target) {
        return name.contains(target);
    }
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.name);
    }
}
