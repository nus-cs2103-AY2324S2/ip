package duke.tasks;

import java.time.LocalDateTime;

/**
 * The Task class represents a task in the Duke application.
 * It is an abstract class that provides basic functionality common to all types of tasks.
 */
public abstract class Task implements Comparable<Task> {
    private final String description;
    private String status = "[ ]";

    /**
     * Constructs a Task object with the given description.
     *
     * @param token The description of the task.
     */
    public Task(String token) {
        this.description = token;
    }

    /**
     * Checks if the task has a specific date.
     *
     * @param toFind The date to search for.
     * @return True if the task has the specified date, false otherwise.
     */
    public abstract boolean hasDate(LocalDateTime toFind);

    /**
     * Generates a string representation of the task for writing to file.
     *
     * @return A string representing the task in a format suitable for file storage.
     */
    public String writeObject() {
        return String.format("| %s | %s", this.status, this.description);
    }

    /**
     * Sets the status of the task.
     *
     * @param s The status string to set.
     */
    public void setStatus(String s) {
        this.status = s;
    }

    /**
     * Marks the task as done.
     *
     * @return The updated task object.
     */
    public Task markDone() {
        this.status = "[X]";
        return this;
    }

    /**
     * Marks the task as not done.
     *
     * @return The updated task object.
     */
    public Task unMarkDone() {
        this.status = "[ ]";
        return this;
    }

    /**
     * Checks if the task's description contains a specific word.
     *
     * @param toFind The word to search for.
     * @return True if the description contains the word, false otherwise.
     */
    public boolean descriptionHasWord(String toFind) {
        return this.description.indexOf(toFind) != -1;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return String.format("%s %s", this.status, this.description);
    }
}
