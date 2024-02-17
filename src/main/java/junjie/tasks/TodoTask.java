package junjie.tasks;

import junjie.exceptions.InvalidArgumentException;

/**
 * Represents a task that has no specific date or time associated with it.
 */
public class TodoTask extends Task {
    private static final String INVALID_NAME = "oi the task needs a name la \uD83D\uDE21\uD83D\uDE21";

    /**
     * Constructs a todo task with the specified name, tags and completion status.
     *
     * @param name The name of the task.
     * @param tags The tags of the task.
     * @param isDone The completion status of the task.
     * @throws InvalidArgumentException If the name is empty.
     */
    public TodoTask(String name, String[] tags, boolean isDone) throws InvalidArgumentException {
        super(name, tags, isDone);
        if (name.isEmpty()) {
            throw new InvalidArgumentException(INVALID_NAME);
        }
    }

    /**
     * Constructs a todo task with the specified name and completion status.
     *
     * @param name The name of the task.
     * @param tags The tags of the task
     * @throws InvalidArgumentException If the name is empty.
     */
    public TodoTask(String name, String[] tags) throws InvalidArgumentException {
        this(name, tags, false);
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
        return String.format("T | %d | %s | %s",
                isDone() ? 1 : 0,
                getName(),
                String.join(" ", getTags()));
    }
}
