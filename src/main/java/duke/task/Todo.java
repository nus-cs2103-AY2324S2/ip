package duke.task;

import duke.exception.JosephException;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     *
     * @param description The description of the todo task.
     * @param isDone The status of the todo task.
     */
    public Todo(String description, boolean isDone) throws JosephException {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getDataString() {
        return "T | " + (isDone ? "1" : "0") + " | " + super.getDescription();
    }
}
