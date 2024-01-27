package duke;

import java.time.LocalDate;

/**
 * The Deadline class represents a task with a deadline.
 * It is a subclass of the Task class.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for Deadline.
     *
     * @param description The description of the deadline.
     * @param by The deadline of the deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return The string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + super.description + " (by: " + by + ")";
    }

    /**
     * Returns the string representation of the deadline to be stored in the file.
     *
     * @return The string representation of the deadline to be stored in the file.
     */
    @Override
    public String toFileString() {
        return "D" + " | " + (isDone ? "1" : "0") + " | " + description + " | " +  by;
    }

    /**
     * Returns a Deadline instance from the string representation of the deadline stored in the file.
     *
     * @param str The string representation of the deadline stored in the file.
     * @return The Deadline instance from the string representation of the deadline stored in the file.
     */
    public static Deadline fromFileString(String str) {
        String[] parts = str.split(" \\| ");
        if (!parts[0].equals("D")) {
            return null;
        }
        String description = parts[2].trim();
        LocalDate by = LocalDate.parse(parts[3].trim());
        boolean isDone = parts[1].trim().equals("1");
        Deadline deadline = new Deadline(description, by);
        if (isDone) deadline.markAsDone();
        return deadline;
    }

    /**
     * Returns the date of the deadline.
     *
     * @return The date of the deadline.
     */
    public String getDate() {
        return by.toString();
    }
}
