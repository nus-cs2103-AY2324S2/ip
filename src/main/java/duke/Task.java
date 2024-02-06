package duke;

/**
 * Task class contains a description, and a boolean marking its status of completion.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * The default Task constructor
     * @param description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Class constructor specifying completion status
     * @param description of the task
     * @param isDone denoting task completion status
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Constructor used to clone a task object
     * @param task
     */
    public Task(Task task) {
        this.description = task.description;
        this.isDone = task.isDone;
    }

    /**
     * Gets status icon denoting task completion.
     * @return String "X" if complete, else ""
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task as complete.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as incomplete.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
