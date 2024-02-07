package shodan.tasks;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task entered by the user. Each task
 * needs to have a name, and they also track their
 * completion status.
 */
public abstract class Task {
    private String name;
    private boolean isDone = false;

    /**
     * The DateTimeFormat used by subclasses to format their start and end dates, if they have any.
     */
    protected static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("y-LLL-d HH:mm");

    /**
     * Instantiates a new Task.
     *
     * @param name the name of the task/
     */
    public Task(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    /**
     * Marks the task as done,
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void undone() {
        this.isDone = false;
    }

    /**
     * Gets the completion status of the task.
     *
     * @return True if the task is done, False otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the task name.
     *
     * @return the name of the task.
     */
    public String getName() {
        return this.name;
    }
}
