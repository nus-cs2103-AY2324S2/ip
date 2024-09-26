package adam.task;

/**
 * Represents a task to be stored in the list of tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Returns a task containing its description and is marked by default as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns if the task is done or not done.
     *
     * @return "X" if the task is done, " " if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    public boolean canFind(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Returns the string format of the task for writing to the save file.
     *
     * @return The string format of the task.
     */
    public String toFileString() {
        return (isDone ? "1" : "0") + "," + description;
    }

    /**
     * Returns the string format of the task for printing to the ui.
     *
     * @return The string format of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
