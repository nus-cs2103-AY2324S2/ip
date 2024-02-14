package jayne.task;
import jayne.JayneException;
/**
 * Represents a task. Each task has a description and a status indicating whether it is done.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Constructs a new Task with the given description. The task is initially not done.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        assert description != null : "Description should not be null";
        this.description = description;
        this.isDone = false;
    }
    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }
    public String getDescription() {
        return description;
    }
    /**
     * Checks if the task is completed.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isCompleted() {
        return this.isDone;
    }
    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }
    /**
     * Returns the status icon for the task.
     * '[X]' if the task is done, '[ ]' if it's not.
     *
     * @return the status icon as a String.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }
    /**
     * Converts the task to a format suitable for file storage.
     * The format includes the status and description of the task.
     *
     * @return a String representing the task in a format suitable for file storage.
     */
    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }
    /**
     * Constructs a Task object from a string in the file storage format.
     *
     * @param line the line from the file in the storage format.
     * @return a Task object corresponding to the line.
     * @throws JayneException if the line format is invalid.
     */
    public static Task fromFileFormat(String line) throws JayneException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new JayneException("Invalid line format");
        }
        Task task = new Task(parts[2]);
        if ("1".equals(parts[1])) {
            task.markAsDone();
        }
        return task;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
