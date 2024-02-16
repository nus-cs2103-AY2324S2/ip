package bytebuddy.tasks;

/**
 * The Task class represents a task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description and sets its completion status to false.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task with the given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return True if the task is marked as done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the task as done and returns a message indicating the change.
     *
     * @return A message indicating that the task has been marked as done.
     */
    public String markAsDone() {
        this.isDone = true;
        return "Nice! I've mark this task as done:\n\t\t" + this;
    }

    /**
     * Marks the task as not done and returns a message indicating the change.
     *
     * @return A message indicating that the task has been marked as not done yet.
     */
    public String unmarkAsDone() {
        this.isDone = false;
        return "OK, I've marked this task as not done yet:\n\t\t" + this;
    }

    /**
     * Gets the status icon representing the completion status of the task.
     *
     * @return The status icon ("✓" for done, "✕" for not done).
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : "✕"); // mark done task with ✓
    }

    /**
     * Returns a formatted string representation of the task for writing into an output file.
     * Subclasses must implement this method to provide specific formatting for each type of task.
     *
     * @return A formatted string representation of the task.
     */
    public abstract String getTextFormattedOutput();

    /**
     * Indicates whether some other object is "equal to" this one.
     * Subclasses must override this method to define their own equality criteria.
     *
     * @param obj The reference object with which to compare.
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * Returns a hash code value for the object.
     * Subclasses must override this method to generate a hash code consistent with the equals method.
     *
     * @return A hash code value for this object.
     */
    @Override
    public abstract int hashCode();

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string representation of the task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
