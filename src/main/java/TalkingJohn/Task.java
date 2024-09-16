package talkingjohn;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected String notes = "";
    protected boolean isDone;

    protected boolean haveNotes;

    /**
     * Constructs a task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.haveNotes = false;
    }

    public Task(String description, String notes) {
        this.description = description;
        this.notes = notes;
        this.isDone = false;
        this.haveNotes = true;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task, including its completion status and description.
     */
    public String toString() {
        if (haveNotes) {
            return "[" + (isDone ? "X" : " ") + "] " + description + "[" + notes + "]";
        } else {
            return "[" + (isDone ? "X" : " ") + "] " + description;
        }
    }
}
