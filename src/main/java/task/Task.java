package task;

import andelu.PriorityLevel;

/**
 * A superclass for Task.
 */
public class Task {

    /** The description for this task. */
    private String description;

    /** The status of completion for this task. */
    private boolean isDone;

    private PriorityLevel priorityLevel;

    /**
     * A superclass constructor to create a Task.
     *
     * @param description The title of the Task.
     * @param isDone The status of the Task.
     * @param isDone The priority level of the Task.
     */
    public Task(String description, boolean isDone, PriorityLevel priorityLevel) {
        this.description = description;
        this.isDone = isDone;
        this.priorityLevel = priorityLevel;
    }

    /**
     * Returns the description of the Task.
     *
     * @return The Description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the Task.
     * Returns "X" if the status of the Task is completed.
     * Else, returns " ".
     *
     * @return "X" if isDone is true, else " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the Task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the Task as not complete.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the priority level of the Task.
     *
     * @return priority level of the task.
     */
    public PriorityLevel getPriorityLevel() {
        return this.priorityLevel;
    }
}
