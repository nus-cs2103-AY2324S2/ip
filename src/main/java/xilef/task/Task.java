package xilef.task;

/**
 * A {@code Task} represents a task with a description and a status (done or not done).
 */
public class Task {

    /**
     * The description of the {@code Task}.
     */
    public String description;

    /**
     * The status of the {@code Task}.
     */
    protected boolean isDone;

    /**
     * Creates a new {@code Task} with the given description.
     *
     * @param description The description of the {@code Task}.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the {@code Task}.
     *
     * @return The status icon of the {@code Task}.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Marks the {@code Task} as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the {@code Task} as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    public String toStringForFile() {
        String d = "";
        if (this.isDone) {
            d = "1";
        } else {
            d = "0";
        }
        return  d + " | " + this.description;
    }
}
