package Bentley;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {

    /**
     * The description of the task.
     */
    private String taskDescription;

    /**
     * The completion status of the task. True if the task is done, false otherwise.
     */
    private boolean isDone;

    /**
     * Constructs a Task object with the specified task description.
     *
     * @param taskDescription The description of the task.
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * Returns a string representation of the completion status. "1" for done, "0" for not done.
     *
     * @return A string representation of the completion status.
     */
    public String doneOrNot() {
        return (isDone ? "1" : "0"); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a formatted string representation of the Task object.
     *
     * @return A string containing the completion status and task description.
     */
    @Override
    public String toString() {
        return "|" + doneOrNot() + "| " + taskDescription;
    }
}
