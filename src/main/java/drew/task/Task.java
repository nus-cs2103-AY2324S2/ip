package drew.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets task completion symbol.
     * @return "X" indicates completed, " " indicates incomplete.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return description;
    }

    /**
     * @return Task description and completion status.
     */
    public String toStatusString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public abstract String toSaveFormatString();

    public void setDone() {
        isDone = true;
    }

    public void setNotDone() {
        isDone = false;
    }
}
