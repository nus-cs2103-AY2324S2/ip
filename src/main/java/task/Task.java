package task;

/**
 * Represents a task.
 *
 * @author Maximilliano Utomo
 */
public abstract class Task {
    /**
     * The task name or description.
     */
    protected String desc;
    /**
     * Marker whether the task is done or not.
     */
    protected boolean isDone;

    /**
     * A public constructor for the task.
     * A task is not done by default.
     * @param desc - the description of the task
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Returns the representative icon for the status of the task.
     * @return A String representing the icon
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Represent the task into stored data.
     * @return A String representing the data information of the task.
     */
    public String toDataFormat() {
        if (this.isDone) {
            return "1|" + this.desc;
        } else {
            return "0|" + this.desc;
        }
    }

    /**
     * Represent the task into a String format applicable for printing output.
     * @return A String representation of the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + this.desc;
    }
}
