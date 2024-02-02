package duke.task;

/**
 * Parent class for various tasks.
 */
public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Gets the status of the task.
     * @return [Y] or [N] depends on the status of the task
     */
    public String getStatusIcon() {
        return isDone ? "[Y] " : "[N] ";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + this.name;
    }
}
