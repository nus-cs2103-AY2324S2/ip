package dibo.task;

import java.util.Arrays;

/**
 * The Task class represents a task of the user.
 */
public abstract class Task {
    private String description;
    private boolean isDone;


    /**
     * Constructs a new Deadline object with the specified parameters.
     *
     * @param description The String description of the task.
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * Returns the string representation for the task,
     * usually for the display format in the ui.
     *
     */
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + this.description;
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
     * Returns the string representation of the task,
     * usually for saving in the text file.
     */
    public String getSaveFormat() {
        return (this.isDone ? "1 | " : "0 | ") + this.description;
    }

    /**
     * Returns a boolean value to signal if the task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns true if the description contains ALL the specified keywords.
     *
     * @param keywords The specified keywords.
     */
    public boolean hasKeywords(String[] keywords) {
        return Arrays.stream(keywords)
                .allMatch(keyword -> this.description.contains(keyword));
    }
}
