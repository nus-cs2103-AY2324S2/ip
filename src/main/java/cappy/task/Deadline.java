package cappy.task;

import cappy.parser.Parser;

import java.time.LocalDateTime;

/**
 * Represents a deadline task in the task management system.
 *
 * <p>The {@code Deadline} class encapsulates a deadline task with a description and a due date
 * time.
 */
public class Deadline extends Task {
    /** The type symbol for the Deadline Task. */
    public static final String TYPE_SYMBOL = "D";

    /** The due date time for the Deadline Task. */
    private final LocalDateTime due;

    public Deadline(String description, LocalDateTime due) {
        super(description);
        this.due = due;
    }

    public Deadline(String description, boolean isDone, LocalDateTime due) {
        super(description, isDone);
        this.due = due;
    }

    @Override
    public String toCsv() {
        return TYPE_SYMBOL
                + ","
                + (super.getDone() ? "1" : "0")
                + ","
                + super.getDescription()
                + ","
                + Parser.dateTimeToString(due)
                + ",";
    }

    @Override
    public String toString() {
        return "["
                + TYPE_SYMBOL
                + "]"
                + super.toString()
                + " (by: "
                + Parser.dateTimeToString(due)
                + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Deadline)) {
            return false;
        }
        Deadline other = (Deadline) obj;
        return super.equals(other) && due.equals(other.due);
    }
}
