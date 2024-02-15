package duke;

/**
 * Task class contains a description, and a boolean marking its status of completion.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected int priority;

    /**
     * The default Task constructor, where isDone is default, and priority is set.
     * @param priority of the task
     * @param description of the task
     */
    public Task(String description, int priority) {
        this.description = description;
        this.isDone = false;
        this.priority = priority;
    }

    /**
     * Class constructor, isDone is set, priority is set.
     * @param description of the task
     * @param isDone denoting task completion status
     * @param priority of the task
     */
    public Task(String description, Boolean isDone, int priority) {
        this.description = description;
        this.isDone = isDone;
        this.priority = priority;
    }

    /**
     * Constructor used to clone a task object
     * @param task
     */
    public Task(Task task) {
        this.description = task.description;
        this.isDone = task.isDone;
        this.priority = task.priority;
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
        return "[" + this.getStatusIcon() + "] " + "[P" + priority + "] " + description;
    }
}
