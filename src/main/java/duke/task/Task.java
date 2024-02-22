package duke.task;

import java.io.Serializable;

/**
 * Represents a generic task, providing a common structure and functionality for
 * tasks.
 */
public abstract class Task implements Serializable {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new Task instance with a specified description. The task is
     * initially marked as not done.
     * 
     * @param description The text description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done. Sets the task's completion status to true.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone. Sets the task's completion status to false.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Retrieves the description of the task.
     * 
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    public boolean containsKeyword(String keyword) {
        boolean found = false;
        for (String word : description.split(" ")) {
            if (word.equals(keyword)) {
                found = true;
            }
        }
        return found;
    }

    /**
     * Generates a string representation of the task, including its completion
     * status and description.
     * 
     * @return A string that represents the task, indicating whether it is done and
     *         describing the task.
     */
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    public String getRepresentation() {
        return this.toString();
    }
}
