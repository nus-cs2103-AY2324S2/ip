package zack.tasks;

import java.time.LocalDateTime;

/**
 * Represents a todo task without a specific deadline or event time.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified description and completion status.
     *
     * @param description The description of the todo task.
     * @param isDone      True if the todo task is marked as done, false otherwise.
     */
    public Todo(String description, boolean isDone, LocalDateTime addedTime) {
        super(description, isDone, addedTime);
    }

    /**
     * Returns a string in a specific format for saving to a file.
     *
     * @return A string representation of the Todo task for saving to a file.
     */
    @Override
    public String toFileString() {
        return super.toFileString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
