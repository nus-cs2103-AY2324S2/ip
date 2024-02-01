package task;

/**
 * A superclass for Task.
 */
public class Task {

    /** The description for this task. */
    private String description;

    /** The status of completion for this task. */
    private boolean isDone;

    /**
     * A superclass constructor to create a Task.
     *
     * @param description The title of the Task.
     * @param isDone The status of the Task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the Task.
     *
     * @return The Description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the Task.
     * Returns "X" if the status of the Task is completed.
     * Else, returns " ".
     *
     * @return "X" if isDone is true, else " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the Task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the Task as not complete.
     */
    public void markAsUndone() {
        this.isDone = false;
    }
}