package hal.tasks;

import java.io.Serializable;

/**
 * The `Task` abstract class serves as the base class for various types of tasks in the Duke application.
 * It has to be serializable so that it can be cloned easily.
 */
public abstract class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String identifier;
    private Boolean isDone;
    private final String taskName;


    /**
     * Constructs a new `Task` with the given task name and completion status.
     *
     * @param taskName The name or description of the task.
     * @param done A boolean indicating whether the task is completed (true) or not (false).
     */
    public Task(String taskName, Boolean done) {
        this.taskName = taskName;
        this.isDone = done;
        this.identifier = " ";
    }

    public void markDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

    public String getIdentifier() {
        return identifier;
    }
    public boolean getDone() {
        return isDone;
    }
    @Override
    public String toString() {
        return String.format("[%s] [%s] %s", identifier, isDone ? "X" : "  ", taskName);
    }

    public String getTaskName() {
        return taskName;
    }

    public String[] encode() {
        return new String[]{identifier, isDone.toString(), taskName};
    }
}


