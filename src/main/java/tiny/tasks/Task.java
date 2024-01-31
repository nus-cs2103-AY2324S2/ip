package tiny.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initialize Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initialize Task.
     *
     * @param description Description of the task.
     * @param isDone      Status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks a task as done.
     */
    public void taskDone() {
        this.isDone = true;
    }

    /**
     * Unmarks a task as done.
     */
    public void taskUndone() {
        this.isDone = false;
    }

    public String toSave() {
        return " | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

}
