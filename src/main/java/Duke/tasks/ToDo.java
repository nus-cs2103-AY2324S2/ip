package duke.tasks;

import java.time.LocalDateTime;

/**
 * The ToDo class represents a to-do task in the Duke application.
 * It is a subclass of the Task class and provides specific functionality for to-do tasks.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object with the given description.
     *
     * @param s The description of the to-do task.
     */
    public ToDo(String s) {
        super(s);
    }

    /**
     * Constructs a ToDo object with the given status and description.
     *
     * @param status The status of the to-do task.
     * @param description The description of the to-do task.
     */
    public ToDo(String status, String description) {
        super(description);
        super.setStatus(status);
    }

    /**
     * Generates a string representation of the to-do task for writing to file.
     *
     * @return A string representing the to-do task in a format suitable for file storage.
     */
    @Override
    public String writeObject() {
        return String.format("todo %s \n", super.writeObject());
    }

    /**
     * Returns a string representation of the to-do task.
     *
     * @return A string representing the to-do task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Checks if the to-do task has a specific date (always returns false for to-do tasks).
     *
     * @param toFind The date to search for.
     * @return Always returns false since to-do tasks do not have dates.
     */
    @Override
    public boolean hasDate(LocalDateTime toFind) {
        return false;
    }
}
