package headcube;
/**
 * Represents a task with a description and a completion status.
 * This is a base class for different types of tasks.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor of a Task object with the given description.
     * The task is initially not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon representing the completion status of the task.
     *
     * @return A string representing the status icon ("X" for done, " " for not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void done() {
        isDone = true;
    }

    /**
     * Returns the file format representation of the task, useful for saving to a file.
     *
     * @return A string formatted for file saving.
     */
    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
    // This equals method was taken from chatgpt.
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Task task = (Task) object;
        return description.equals(task.description);
    }
}
