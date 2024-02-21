package seedu.banter.tasks;

import java.time.LocalDateTime;
import java.util.Arrays;


/**
 * Represents a task.
 */
public abstract class Task {
    /**
     * Represents a task that is done.
     */
    public static final String IS_DONE_ICON = "X";

    /**
     * Represents a task that is not done.
     */
    public static final String IS_NOT_DONE_ICON = " ";

    private final String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
        Assertions.assertTaskIsUnmarked(this);
    }

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * String representation of a task.
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }

    String markAsDone() {
        isDone = true;
        return "Nice! I've marked this task as done:\n  " + this;
    }

    String markAsUndone() {
        isDone = false;
        return "OK, I've marked this task as not done yet:\n  " + this;
    }

    /**
     * Returns the status of a task.
     * @return Status of a task.
     */
    public String getStatus() {
        return isDone ? IS_DONE_ICON : IS_NOT_DONE_ICON;
    }

    /**
     * Returns the description of a task.
     * @return Description of a task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns true if a task is done.
     * @return True if a task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns true if the description of the task contains the keyword.
     * @param keywords Keywords to be checked.
     * @return True if the description of the task contains the keyword.
     */
    public boolean contains(String ...keywords) {
        return Arrays.stream(keywords)
                .allMatch(keyword -> description.toLowerCase().contains(keyword.toLowerCase()));
    }

    /**
     * Returns icon representing a task type.
     * @return Icon representing a task type.
     */
    public abstract String getTaskType();

    public abstract LocalDateTime getDateTimePriority();
}
