package scribbles.task;

/**
 * This class represents a task.
 */
public class Task {
    protected String description;
    protected boolean isCompleted;

    /**
     * Constructs a new Task object with the given description.
     *
     * @param description description of task.
     * @param isCompleted true when tasks is completed.
     */
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the task has been completed.
     *
     * @return True if the task is completed.
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Marks the task as completed.
     */
    public void markComplete() {
        this.isCompleted = true;
    }

    /**
     * Marks the class as incompleted.
     */
    public void markIncomplete() {
        this.isCompleted = false;
    }

    /**
     * Returns the String format of how tasks are listed in the list of task.
     *
     * @return User-readable task in String format
     */
    public String toString() {
        if (this.isCompleted) {
            return "[X] " + this.description;
        }
        return "[ ] " + this.description;
    }
}
