package duke;

/**
 * Abstract class representing a general task within the Duke application.
 * This class provides a common structure for tasks, including a description and a completion status.
 */
public abstract class Task {
    protected static final String DATE_TIME_FORMAT_INPUT = "dd/MM/yyyy HHmm";
    protected static final String DATE_TIME_FORMAT_OUTPUT = "MMM dd yyyy, h:mma";
    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The completion status of the task. True if the task is completed, false otherwise.
     */
    protected boolean isDone;
    /**
     * Constructs a new Task instance.
     *
     * @param description The textual description of the task.
     * @param isDone The initial completion status of the task.
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Generates and returns a string representation of the task formatted for file storage.
     * The specific format is defined by subclasses.
     *
     * @return A string formatted for saving the task to a file.
     */
    abstract String toFileFormat();

    /**
     * Returns the status icon of the task, indicating completion.
     *
     * @return A string representing the status icon.
     *      "X" indicates the task is completed, and a space indicates it is not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done by setting the completion status to true.
     */
    public String getDescription() {
        return this.description; // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by resetting the completion status to false.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Provides a string representation of the task for display purposes, including the status icon and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
