package lia;

/**
 * The Task class represents a generic task with a description and completion status.
 * It serves as the base class for more specific task types like Todo, Deadline, and Event.
 */
public class Task {
    String description;
    boolean isDone;

    /**
     * Constructs a Task object with the given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task (true if done, false otherwise).
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
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
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the task type icon based on the specific task type (Todo, Deadline, Event).
     *
     * @return A string representing the task type icon.
     */
    public String getTaskIcon() {
        if (this instanceof Todo) {
            return "T";
        } else if (this instanceof Deadline) {
            return "D";
        } else if (this instanceof Event) {
            return "E";
        } else {
            return "";
        }
    }

    /**
     * Returns the status icon based on the completion status (X for done, space for not done).
     *
     * @return A string representing the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a string representation of the task, including type, status, and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getTaskIcon() + "]" + "[" + getStatusIcon() + "] " + description;
    }
}
