package duke.task;

import duke.task.Task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String status = getStatusIcon();
        String todoStatus = "[T][" + status + "] ";
        String desc = super.toString();

        return todoStatus + desc;
    }

    @Override
    public String toFileString() {
        String todoStatus = "T | " + (isDone ? "1" : "0");

        return todoStatus + " | " + description;
    }
}
