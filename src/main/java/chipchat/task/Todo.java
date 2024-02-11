package chipchat.task;

import chipchat.action.CommandType;

/**
 * Represents a simple type of task that only has a description and completion status. Subclass of Task.
 */
public class Todo extends Task {

    /**
     * Constructor of Todo.
     *
     * @param description name/description of the task
     * @param isDone completion status of the task
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Returns the data-format string of the task. For Chipchat-specific storage purposes.
     *
     * @return a string representation of the task in a parsable format
     */
    @Override
    public String dataString() {
        return String.format("%s /isDone %s", CommandType.TODO, super.dataString());
    }
}
