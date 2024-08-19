package pan;

import pan.enums.TaskStatus;

/**
 * Task - Represents the Task class for a Task instance
 * @author Jerome Goh
 */
public class Task {
    private String description;
    private TaskStatus isDone;

    /**
     * Constructs a Task instance.
     *
     * @param description The description of the task.
     * @param isDone The status of whether the task has been completed.
     */
    public Task(String description, TaskStatus isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Converts the Event instance into its correpsonding string representation.
     *
     * @return String that represents whether the corresponding Task has been comopleted.
     */
    @Override
    public String toString() {
        return this.isDone.name().equals("COMPLETE") ? "[X] " + this.description : "[ ] " + this.description;
    }

    /**
     * Gets the description attribute of the task.
     *
     * @return String representing the description or body of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the isDone attribute of the task.
     *
     * @return TaskStatus Enum that reflects whether the task status has been completed or not.
     */
    public TaskStatus getIsDone() {
        return this.isDone;
    }

    /**
     * Updates the description attribute of the task.
     *
     * @param newDescription Updated description attribute of the task.
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Updates the isDone attribute of the task.
     *
     * @param newIsDone Updated isDone attribute of the task.
     */
    public void setIsDone(TaskStatus newIsDone) {
        this.isDone = newIsDone;
    }
}
