package tasks;

/**
 * Represents something that needs to be done by the user.
 */
public class Task {
    private final String description;
    private boolean isComplete;

    /**
     * Creates a task object.
     *
     * @param description Indicates what the task is about.
     */
    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    /**
     * Creates a task object.
     *
     * @param description Indicates what the task is about.
     * @param isComplete Indicates whether the task has been completed or not.
     */
    public Task(String description, boolean isComplete) {
        this.description = description;
        this.isComplete = isComplete;
    }

    /**
     * Works as a setter to set the task as completed or not.
     *
     * @param isComplete Indicates whether the task has been completed or not.
     */
    public void setStatus(boolean isComplete) {
        this.isComplete = isComplete;
    }

    /**
     * Provides a text version of the task that can be presented to the user.
     *
     * @return text version of the task
     */
    public String stringify() {
        String done = "[ ]";
        if (this.isComplete) {
            done = "[X]";
        }
        return done + " " + this.description;
    }

    /**
     * Provides a text version of the task to be stored in the storage.
     *
     * @return text version of the task
     */
    public String toString() {
        String done = "O";
        if (this.isComplete) {
            done = "X";
        }
        return done + " | " + this.description;
    }

    /**
     * Searches for keyword in the task description.
     *
     * @param keyword Keyword.
     * @return True if search matches, false otherwise.
     */
    public boolean search(String keyword) {
        if (this.description.contains(keyword)) {
            return true;
        }
        return false;
    }
}