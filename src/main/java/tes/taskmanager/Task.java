package tes.taskmanager;

/**
 * Represents a task in the task list.
 *
 * @author Wong Xu Cheng
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status of the task.
     * X for done, empty space for not done.
     *
     * @return Icon of status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return " | " + this.getStatusIcon() + " | " + this.description;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task.
     */
    public void unmark() {
        this.isDone = false;
    }

}
