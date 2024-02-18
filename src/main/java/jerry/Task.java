package jerry;

import java.time.LocalDate;

/**
 * Represents the abstract concept of a task in the chatbot application. This class serves as a base for
 * different types of tasks (e.g., ToDo, Deadline, Event) and provides common properties and methods that are shared
 * across all task types.
 * <p>
 * Each task has a description and a status indicating whether it is done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description. The task is initially not done.
     *
     * @param description The text description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markNotDone() {
        isDone = false;
    }

    /**
     * Returns the type icon representing the task type.
     *
     * @return A string representing the type icon.
     */
    public abstract String getTypeIcon();

    /**
     * Provides a string representation of this task, including its status (done or not done) and description.
     * This method is intended to be overridden by subclasses to include additional information specific to the task type.
     *
     * @return A string representing this task, including its status and description.
     */
    @Override
    public String toString() {
        return "[" + getTypeIcon() + "]" + (isDone ? "[X] " : "[ ] ") + description;
    }

    /**
     * Returns a string representation of this task meant to be saved in cache file.
     * @return A string to be saved in file.
     */
    public abstract String toFileFormat();

    // For testing purposes
    /**
     * Returns the description of this task.
     *
     * @return A string representing the task's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Determines whether this task is marked as done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Checks if the task is scheduled for the specified date.
     * @param date The date to check the task against.
     * @return true if the task occurs on the specified date, false otherwise.
     */
    public abstract boolean isScheduledForDate(LocalDate date);
}

