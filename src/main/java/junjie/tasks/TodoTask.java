package junjie.tasks;

import junjie.exceptions.InvalidArgumentException;

/**
 * Represents a task that has no specific date or time associated with it.
 */
public class TodoTask extends Task {
    private static final String INVALID_NAME = "oi the task needs a name la \uD83D\uDE21\uD83D\uDE21";

    /**
     * Constructs a new TodoTask with the given name and completion status.
     *
     * @param name   The name of the task.
     * @param isDone The completion status of the task.
     * @throws InvalidArgumentException If the name is empty.
     */
    public TodoTask(String name, boolean isDone) throws InvalidArgumentException {
        super(name, isDone);
        if (name.isEmpty()) {
            throw new InvalidArgumentException(INVALID_NAME);
        }
    }

    /**
     * Constructs a new TodoTask with the given name and sets its completion status to false.
     *
     * @param name The name of the task.
     * @throws InvalidArgumentException If the name is empty.
     */
    public TodoTask(String name) throws InvalidArgumentException {
        this(name, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the task to be written to the file.
     *
     * @return A string representation of the task to be written to the file.
     */
    @Override
    public String toFileString() {
        return String.format("T | %d | %s", isDone() ? 1 : 0, getName());
    }
}
