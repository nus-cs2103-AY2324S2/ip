package duke;

/**
 * Represents a task with a description and a boolean indicating its completion status.
 */
public class Task {
    protected String taskDescription;
    protected boolean isTaskDone;

    /**
     * Constructs a task with the specified description and sets its completion status to false.
     *
     * @param taskDescription the description of the task
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isTaskDone = false;
    }

    /**
     * Constructs a task with the specified description and completion status.
     *
     * @param taskDescription the description of the task
     * @param isTaskDone      the completion status of the task
     */
    public Task(String taskDescription, Boolean isTaskDone) {
        this.taskDescription = taskDescription;
        this.isTaskDone = isTaskDone;
    }

    /**
     * Constructs a task by cloning another task object.
     *
     * @param task the task to be cloned
     */
    public Task(Task task) {
        this.taskDescription = task.taskDescription;
        this.isTaskDone = task.isTaskDone;
    }

    /**
     * Gets the status icon indicating the completion status of the task.
     *
     * @return "X" if the task is complete, otherwise " "
     */
    public String getStatusIcon() {
        return (isTaskDone ? "X" : " ");
    }

    /**
     * Marks the task as complete.
     */
    public void markDone() {
        this.isTaskDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void unmarkDone() {
        this.isTaskDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + taskDescription;
    }
}
