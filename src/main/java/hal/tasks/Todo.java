package hal.tasks;

/**
 * The `Todo` class represents a task without a specific deadline or time range.
 * It extends the `Task` class and inherits its properties and methods.
 * It has to be serializable so that it can be cloned easily.
 */
public class Todo extends Task {
    private static final long serialVersionUID = 2L;
    public Todo(String taskName) {
        this(taskName, false);
    }

    /**
     * Constructs a new `Todo` task with the given task name and completion status.
     *
     * @param taskName The name or description of the `Todo` task.
     * @param done A boolean indicating whether the task is completed (true) or not (false).
     */

    public Todo(String taskName, Boolean done) {
        super(taskName, done);
        super.identifier = "T";
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getTaskName() {
        return super.getTaskName();
    }
}
