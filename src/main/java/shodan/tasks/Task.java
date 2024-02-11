package shodan.tasks;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task entered by the user. Each task
 * needs to have a name, and they also track their
 * completion status.
 */
public abstract class Task {
    /**
     * The DateTimeFormat used by subclasses to format their start and end dates, if they have any.
     */
    protected static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("y-LLL-d HH:mm");

    private String name;
    private boolean isDone = false;
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
        return "[" + (isDone ? "X" : " ") + "] " + name;
    }

    /**
     * Sets whether the task is done or not done.
     *
     * @param isDone the status to set the task to.
     */

    public void setDone(boolean isDone) {
        this.isDone = isDone;
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
        return name;
    }
}
