package ben.tasks;

/**
 * Represents a generic task in the Ben task management application.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified attributes.
     *
     * @param isDone      Indicates if the task is done or not.
     * @param description The description of the task.
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the status icon for the task.
     *
     * @return A string representing the status icon.
     */
    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    /**
     * Converts the task to a string representation for saving to a file.
     *
     * @return A string representing the task for saving to a file.
     */
    public String saveTask() {
        return (this.isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Checks if the description of the task contains the specified keyword.
     *
     * @param keyword The keyword to check for in the description.
     * @return True if the description contains the keyword, false otherwise.
     */
    public boolean contains(String keyword) {
        return this.description.contains(keyword);
    }
}
