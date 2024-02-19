package Kokbot.task;

import java.time.LocalDateTime;

/**
 * Represents a Task
 */
abstract public class Task {

    /**
     * Description of the Task
     */
    protected String description;

    /**
     * Whether the Task is done
     */
    protected boolean isDone;

    /**
     * Format for dateTimes to be printed to the user
     */
    protected String dateTimeFormat = "MMM d yyyy h:mma";

    /**
     * Constructor for Task
     *
     * @param description Description of the Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    abstract public String getType();
    abstract public LocalDateTime getDateTime();

    public String getDescription() {
        return this.description;
    }
    /**
     * Returns the status icon of the Task
     *
     * @return Status icon of the Task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the overview of the Task in String format
     *
     * @return Overview of the Task in String format
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Returns the overview of the Task in String format for saving to file
     *
     * @return Overview of the Task in String format for saving to file
     */
    public String toFileString() {
        return String.format("%s,%s", this.getStatusIcon(), this.description);
    }

    /**
     * Marks the current Task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the current Task as undone
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    public boolean matchesKeyword(String keyword) {
        return this.description.contains(keyword);
    }
}