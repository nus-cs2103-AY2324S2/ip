package tasks;

/**
 * Represents a generic task.
 */
public class Task {

    protected String name;
    protected boolean isCompleted;

    /**
     * Constructs a Task object with the specified name.
     *
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markTask() {
        isCompleted = true;
    }

    /**
     * Sets the task as completed.
     */
    public void setCompleted() {
        isCompleted = true;
    }

    /**
     * Unmarks the task as completed.
     */
    public void unmarkTask() {
        isCompleted = false;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return "X" if completed, otherwise " "
     */
    public String getStatusIcon() {
        return (isCompleted ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the name of the task.
     *
     * @return a string containing the name of the task
     */
    public String getName() {
        return name;
    }

    /**
     * Converts the task to a string formatted for writing to file.
     *
     * @return a string containing the status and name of the task
     */
    public String toWrite() {
        return (isCompleted ? "1" : "0") + " | " + name;
    }

    /**
     * Returns a string representation of the Task object.
     *
     * @return a string containing the status icon and name of the task
     */
    @Override
    public String toString() {
        assert (getStatusIcon().equals("X") || getStatusIcon().equals(" ")) : "Invalid status icon!";
        return "[" + getStatusIcon() + "] " + name;
    }
}
