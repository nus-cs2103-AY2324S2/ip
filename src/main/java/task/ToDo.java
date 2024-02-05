package task;

import exception.InvalidTaskFormatException;

/**
 * Represents a to-do task.
 * <p>
 * This class extends the {@link Task} class and is used to represent tasks that
 * have no specific deadline or start and end date. It provides a constructor to
 * create a new to-do task with a description.
 * </p>
 */
public class ToDo extends Task {

    /**
     * Constructs a new {@code ToDo} instance with the specified description.
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a new {@code ToDo} instance with the specified description.
     * @param input The input string to be parsed into a to-do task.
     */
    public static ToDo createFromInput(String input) throws InvalidTaskFormatException {
        try {
            String description = input.split("todo ")[1];
            return new ToDo(description);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskFormatException("Invalid todo format. Please use 'todo description'.");
        }
    }

    /**
     * Returs the string representation of the to-do task.
     * @return The string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the to-do task to be saved in the file.
     * @return The string representation of the to-do task to be saved in the file.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
