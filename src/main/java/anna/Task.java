package anna;

import java.time.format.DateTimeFormatter;

/**
 * The Task class represents a task.
 */
public abstract class Task {

    protected static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern(
        "d/M/yyyy HH:mm"
    );
    protected static final DateTimeFormatter DATETIME_PRINT_FORMAT = DateTimeFormatter.ofPattern(
        "MMM dd yyyy HH:mm"
    );
    protected static final DateTimeFormatter LOCALTIME_FORMAT = DateTimeFormatter.ofPattern(
        "HH:mm:ss"
    );
    protected boolean isDone;
    protected TaskID id;
    protected String task;

    /**
     * Constructs a new Task with the specified task description, done status, and ID.
     *
     * @param task the description of the task
     * @param isDone the status indicating whether the task is done or not
     * @param id the ID of the task
     */
    Task(String task, boolean isDone, TaskID id) {
        this.isDone = isDone;
        this.id = id;
        this.task = task;
    }

    /**
     * Checks if the task is done.
     *
     * @return true if the task is done, otherwise false
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the status of the task to done or not done.
     *
     * @param isDone the status to set
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Gets the ID of the task.
     *
     * @return the ID of the task
     */
    public String taskId() {
        return id.toString();
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string representation of the task
     */
    public String toString() {
        return String.format("[%s][%s] %s", id, isDone ? "X" : " ", taskStr());
    }

    /**
     * Serializes the task to a string.
     *
     * @return the serialized string representation of the task
     */
    public String serialise() {
        return String.format("%s<0>%s<0>%s<1>", id, task, isDone ? "X" : " ");
    }

    /**
     * Gets the string representation of the task.
     *
     * @return the string representation of the task
     */
    public abstract String taskStr();
}
