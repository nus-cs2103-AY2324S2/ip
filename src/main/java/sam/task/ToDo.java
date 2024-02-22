package sam.task;

import sam.SamException;

public class ToDo extends Task {
    public ToDo(String description) throws SamException {
        super(description);
        if (description.isBlank()) {
            throw new SamException("Please provide a task description.");
        }
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
