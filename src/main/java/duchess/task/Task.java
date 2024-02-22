package duchess.task;

import java.util.Objects;

/**
 * Task class represents a generic task in the Duchess program.
 * It provides methods to manipulate tasks, such as marking them as done and converting them to string format.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the given description and sets its completion status to false.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task object with the given description and completion status.
     *
     * @param description the description of the task
     * @param isDone true if the task is completed, false otherwise
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon representing whether the task is done or not.
     *
     * @return "X" if the task is done, " " (space) otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //mark done task with X
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
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string representing the task's completion status and description
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }


    /**
     * Returns a string representation of the task in file format.
     *
     * @return a string representing the task's type, completion status, and description for file storage
     */
    public String toFileString() {
        return "duchess.task.Task |" + (isDone ? "1" : "0")
                + " | " + description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Task other = (Task) obj;
        // Compare the description fields for equality
        return Objects.equals(description, other.description);
    }

    @Override
    public int hashCode() {
        // Generate a hash code based on the description field
        return Objects.hash(description);
    }
}
