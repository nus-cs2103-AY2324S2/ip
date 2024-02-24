package sam.task;

import sam.SamException;

/**
 * Represents a todo task.
 *
 * Inherits from the Task class.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description the description of the ToDo task
     * @throws SamException if there is an issue with the provided description
     */
    public ToDo(String description) throws SamException {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return String.format("T | %d | %s", isDone ? 1 : 0, this.description);
    }
}
