package jimmy.tasks;

import java.util.Objects;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo class.
     *
     * @param taskName Name of the task.
     */
    public Todo(String taskName, boolean isCompleted) {
        super(taskName, isCompleted);
    }

    /**
     * Details regarding the todo.
     *
     * @return String representation of a todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Format of the todo to be saved in the file.
     *
     * @return String representation of a todo.
     */
    public String toFileString() {
        return String.format("%s | %d | %s", "T", Objects.equals(super.getStatus(), "X") ? 1 : 0, super.getDesc());
    }
}

