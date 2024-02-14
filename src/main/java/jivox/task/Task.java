package jivox.task;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Task is an abstract class representing a generic task.
 * Specific task types extend Task.
 */
public abstract class Task {
    private boolean isDone;
    private final String content;

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


    public abstract String saveFormat();

    /**
     * Gets the type identifier for this task.
     *
     * @return The type identifier.
     */
    public abstract String getType();

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
    public boolean equals(Object o){
        if(o instanceof Task){
            Task t = (Task) o;
            return Objects.equals(t.content, this.content);
        }
        else{
            return false;
        }
    }
}
