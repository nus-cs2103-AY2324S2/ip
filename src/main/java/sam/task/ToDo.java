package sam.task;

import sam.SamException;

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
