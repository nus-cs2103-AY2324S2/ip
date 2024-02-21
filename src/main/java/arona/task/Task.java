package arona.task;

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
     * Checks whether the task description contains
     * the input keyword.
     *
     * @param keyword - the String representing the keyword
     * @return True if contains keyword and False otherwise
     */
    public boolean hasKeyword(String keyword) {
        return desc.toLowerCase().contains(keyword.toLowerCase());
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
