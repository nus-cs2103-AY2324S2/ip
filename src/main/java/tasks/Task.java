package tasks;

/**
 * Represents a generic task.
 */
public class Task {

    protected String name;
    protected boolean completed;

    /**
     * Constructs a Task object with the specified name.
     *
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markTask() {
        this.completed = true;
    }

    /**
     * Sets the task as completed.
     */
    public void setCompleted() {
        this.completed = true;
    }

    /**
     * Unmarks the task as completed.
     */
    public void unmarkTask() {
        this.completed = false;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return "X" if completed, otherwise " "
     */
    public String getStatusIcon() {
        return (completed ? "X" : " "); // mark done task with X
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
        return (completed ? "1" : "0") + " | " + name;
    }

    /**
     * Returns a string representation of the Task object.
     *
     * @return a string containing the status icon and name of the task
     */
    @Override
    public String toString() {
        assert (getStatusIcon().equals("X") || getStatusIcon().equals(" ")) : "Invalid status icon!";
        return "[" + this.getStatusIcon() + "] " + name;
    }
}
