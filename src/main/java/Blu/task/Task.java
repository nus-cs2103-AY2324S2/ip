package blu.task;

/**
 * Represents the base class for a task in the Blu application.
 * This abstract class provides common fields and methods for various types of tasks.
 */
public abstract class Task {
    private String title;
    private boolean isMarked;

    /**
     * Creates a new Task with the given title.
     * Initially, the task is not marked.
     *
     * @param title The title of the task.
     */
    public Task(String title) {
        this.title = title;
        this.isMarked = false;
    }

    /**
     * Returns the title of the task.
     *
     * @return The title of the task.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Marks the task as completed.
     */
    public void setMarked() {
        this.isMarked = true;
    }

    /**
     * Unmarks the task as not completed.
     */
    public void setUnmarked() {
        this.isMarked = false;
    }

    /**
     * Returns whether the task is marked as completed.
     *
     * @return true if the task is marked as completed, false otherwise.
     */
    public boolean isCompleted() {
        return isMarked;
    }

    /**
     * Converts the task to its CSV string representation.
     * This method is abstract and must be implemented by subclasses.
     *
     * @return The CSV string representation of the task.
     */
    public abstract String toCsv();

    @Override
    public String toString() {
        if (this.isMarked) {
            return "[X] " + this.title;
        }
        return "[ ] " + this.title;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Task)) {
            return false;
        }
        Task other = (Task) obj;
        return this.title.equals(other.title) && this.isMarked == other.isMarked;
    }
}
