package jivox.task;
import java.time.LocalDateTime;


/**
 * Task is an abstract class representing a generic task.
 * Specific task types extend Task.
 */
public abstract class Task {
    private boolean isDone;
    private final String content;

    private Tag tag;

    /**
     * Creates a new Task with the given content/description.
     *
     * @param content The task description.
     */
    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    public boolean contains(String input) {
        return content.toLowerCase().contains(input.toLowerCase());
    }

    /**
     * Marks this task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Gets the deadline for this task.
     * The base implementation returns null.
     *
     * @return The deadline, or null if none.
     */
    public LocalDateTime getDeadline() {
        return null;
    }

    /**
     * Unmarks this task as completed.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Gets the description of this task.
     *
     * @return The description.
     */
    public String getDescription() {
        return this.content;
    }

    /**
     * Sets the tag of this task.
     *
     */
    public void setTag(Tag tag) {
        this.tag = tag;
    }


    public abstract String saveFormat();

    /**
     * Gets the type identifier for this task.
     *
     * @return The type identifier.
     */
    public abstract String getType();

    /**
     * returns the tag associated with the task.
     *
     * @return The type identifier.
     */
    public String getTag() {
        return this.tag == null ? "" : this.tag.toString();
    }

    /**
     * Checks if this task is marked as completed.
     *
     * @return true if completed, false otherwise.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns a string representation of this task.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        String mark = isDone ? "X" : " ";
        return "[" + mark + "] " + this.content;
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof Task) {
            Task t = (Task) o;
            return this.content.equalsIgnoreCase(t.content);
        } else {
            return false;
        }
    }
}
