package duke.task;

import java.util.Objects;

/**
 * The class representing an Event task.
 * */
public class Todo extends Task {
    /* Type for Todo task. */
    public static final String TYPE = "T";
    /* Type indicator for Event task. */
    public static final String TYPE_INDICATOR = "[T]";
    /* Start date of the event. */

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return TYPE_INDICATOR + getDisplay() + " " + getDescription();
    }

    @Override
    public String toDbString() {
        return TYPE + super.toDbString();
    }
}
