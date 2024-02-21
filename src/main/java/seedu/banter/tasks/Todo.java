package seedu.banter.tasks;

import java.time.LocalDateTime;


/**
 * Represents a task of type Todo in a task list.
 * A Todo task has a description and a boolean indicating whether it is done.
 */
public class Todo extends Task {
    private static final String TODO_ICON = "T";

    /**
     * Constructs a Todo task that should be unmarked.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
        Assertions.assertTaskIsUnmarked(this);
    }

    /**
     * Constructs a Todo task without restrictions on done status.
     *
     * @param description The description of the Todo task.
     * @param isDone The done status of the Todo task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the icon representing a Todo task.
     *
     * @return The icon representing a Todo task.
     */
    @Override
    public String getTaskType() {
        return TODO_ICON;
    }

    /**
     * Returns the string representation of a Todo task.
     *
     * @return The string representation of a Todo task.
     */
    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString();
    }

    /**
     * Returns maximum datetime as sorting priority of a Todo task.
     * @return Maximum datetime as sorting priority of a Todo task.
     */
    public LocalDateTime getDateTimePriority() {
        return LocalDateTime.MAX;
    }
}
