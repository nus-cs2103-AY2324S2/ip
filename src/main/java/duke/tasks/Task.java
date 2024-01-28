package duke.tasks;

/**
 * The `Task` abstract class serves as the base class for various types of tasks in the Duke application.
 */
public abstract class Task {
    protected String identifier;
    private Boolean done;
    private String taskName;


    /**
     * Constructs a new `Task` with the given task name and completion status.
     *
     * @param taskName The name or description of the task.
     * @param done A boolean indicating whether the task is completed (true) or not (false).
     */
    public Task(String taskName, Boolean done) {
        this.taskName = taskName;
        this.done = done;
        this.identifier = "";
    }

    public void markDone() {
        done = true;
    }

    public void markUndone() {
        done = false;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return String.format("[%s] [%s] %s", identifier, done ? "X" : " ", taskName);
    }

    public String[] encode() {
        return new String[]{identifier, done.toString(), taskName};
    }

}


