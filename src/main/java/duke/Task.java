package duke;
import java.io.Serializable;

/**
 * Represents a task with a description and a completion status.
 *
 * The Task class contains methods for managing task descriptions and completion status.
 * Each task can be marked as done or undone, and its status can be retrieved.
 * 
 */
public class Task implements Serializable{
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon representing the completion status of the task.
     *
     * @return A string containing "X" if the task is done, or a space if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as completed by setting the completion status to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Resets the completion status of the task to incomplete by setting it to false.
     */
    public void markAsUnDone() {
        isDone = false;
    }

    /**
     * Returns a string of the Task.
     *
     * @return A string representation of the Task object.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Task)) {
            return false;
        }
        Task other = (Task) obj;
        return this.description.equals(other.description);
    }
}