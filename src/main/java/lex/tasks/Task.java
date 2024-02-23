package lex.tasks;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents a task.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Todo.class, name = "TODO"),
    @JsonSubTypes.Type(value = Deadline.class, name = "DEADLINE"),
    @JsonSubTypes.Type(value = Event.class, name = "EVENT")
})
public abstract class Task {
    /**
     * The type of the task.
     */
    public final String type = this.getClass().getSimpleName().toUpperCase();

    /**
     * The title of the task.
     */
    protected String title;

    /**
     * Whether the task is done.
     */
    protected boolean isDone;

    /**
     * Constructor for the Task class.
     *
     * @param title The title of the task.
     */
    public Task(@JsonProperty("title") String title) {
        this.title = title;
        this.isDone = false;
    }

    /**
     * Gets the title of the task.
     *
     * @return The title of the task.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the task.
     *
     * @param title The title of the task.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets whether the task is done.
     *
     * @return Whether the task is done.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Sets whether the task is done.
     *
     * @param isDone Whether the task is done.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String isDoneIcon = isDone ? "X" : " ";
        return String.format("[%s] %s", isDoneIcon, title);
    }
}
