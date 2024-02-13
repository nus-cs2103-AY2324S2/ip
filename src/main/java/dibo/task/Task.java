package dibo.task;

import java.util.Arrays;

/**
 * Class to represent a task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;


    /**
     * Constructor for the Task class.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * Returns the string representation for the task,
     * usually for the display format in the ui.
     *
     * @return The string representation of the task for displaying.
     */
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + this.description;
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task,
     * usually for saving in the text file.
     *
     * @return The string representation of the task for saving.
     */
    public String getSaveFormat() {
        return (this.isDone ? "1 | " : "0 | ") + this.description;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns true if the description contains the specified keywords.
     *
     * @param keywords The specified keywords.
     * @return True if the description has all the keywords, false otherwise.
     */
    public boolean hasKeywords(String[] keywords) {
        return Arrays.stream(keywords)
                .allMatch(keyword -> this.description.contains(keyword));
    }
}
