package tasks;

import utils.DateFormatter;

/**
 * Represents a deadline, which is a specialized form of a Task
 * This class adds a specific deadline to the task description.
 */
public class Deadline extends Task {
    private static final String DEADLINE_PREFIX = "[D]";
    private final String deadline;

    /**
     * Constructs a new task.Deadline with the given description and deadline.
     *
     * @param description   The description of the deadline.
     * @param deadline      The deadline date as a String.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = DateFormatter.formatDate(deadline.trim());
    }

    /**
     * Converts the Deadline object to a string format suitable for storing in a file.
     *
     * @return The string representation of the Deadline object for file storage.
     */
    @Override
    public String convertTaskToFileString() {
        return String.format("D|%s|%s|%s", super.isDone() ? "1" : "0", description.trim(), deadline);
    }

    /**
     * Returns a string representation of the Deadline
     * The string includes the deadline identifier, followed by the task's string representation and the
     * specified deadline.
     */
    @Override
    public String toString() {
        return DEADLINE_PREFIX + super.toString() + " (by: " + deadline + ")";
    }
}
