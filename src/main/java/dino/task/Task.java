package dino.task;

/** Represents a Task with a description and completion status. */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon indicating the completion status of the task.
     *
     * @return "1" if the task is done, "0" otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    /**
     * Marks the task as done and prints the status icon and description.
     *
     * @return String representation.
     */
    public String markAsDone() {
        this.isDone = true;
        return "Good job on completing the task! I have checked it off the list.\n"
                + getStatusIcon() + " | " + description + "\n";
    }

    /**
     * Marks the task as undone and prints the status icon and description.
     *
     * @return String representation.
     */
    public String markAsUndone() {
        this.isDone = false;
        return "Ah, I will mark it as undone. Remember to do it asap!\n"
                + getStatusIcon() + " | " + description + "\n";
    }

    /**
     * Returns a boolean representation whether Task contains the keyword.
     *
     * @param keyword String value input.
     * @return        True if description contains keyword, else False.
     */
    public boolean containsKeyword(String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return description;
    }
}


