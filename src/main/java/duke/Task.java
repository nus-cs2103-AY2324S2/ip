package duke;

/**
 * Represents a task object
 */
public class Task {
    protected String taskDescription;
    protected boolean isComplete;

    /**
     * Constructs a task with the specified description
     * Completion status by default is false
     *
     * @param taskDescription the description of the task
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isComplete = false;
    }

    /**
     * Creates a task with the specified description and completion status.
     *
     * @param taskDescription description of the task
     * @param isComplete      completion status of the task
     */
    public Task(String taskDescription, Boolean isComplete) {
        this.taskDescription = taskDescription;
        this.isComplete = isComplete;
    }

    /**
     * Creates a task by duplicating another task object.
     *
     * @param task the task to be cloned
     */
    public Task(Task task) {
        this.taskDescription = task.taskDescription;
        this.isComplete = task.isComplete;
    }


    /**
     * Marks a task as complete.
     */
    public void markDone() {
        this.isComplete = true;
    }

    /**
     * Marks the previously marked task as incomplete.
     */
    public void unmarkDone() {
        this.isComplete = false;
    }

    /**
     * Gets the status icon to indicate the completion status of the task.
     *
     * @return "X" for completed tasks, otherwise it will be " "
     */
    public String getStat() {
        return (isComplete ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + this.getStat() + "] " + taskDescription;
    }
}
