package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@link Deadline} class represents a task with a specific deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime date;

    /**
     * Constructs a {@link Deadline} instance with the specified title and deadline date.
     *
     * @param title The title of the deadline task.
     * @param date The deadline date and time.
     */
    public Deadline(String title, LocalDateTime date) {
        super(title);
        this.date = date;
    }

    /**
     * Returns a string representation of the deadline task, including its type, title, and deadline details.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mma"))
                + ", " + date.getDayOfWeek() + ")";
    }
}
