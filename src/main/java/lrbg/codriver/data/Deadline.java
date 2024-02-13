package lrbg.codriver.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    /** The date of the deadline. */
    private final LocalDate date;

    /**
     * Constructor for Deadline.
     * @param description The description of the task.
     * @param date The date of the deadline.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(
                DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileSaveString() {
        return "D|" + super.toFileSaveString() + "|" + this.date;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Deadline) {
            Deadline other = (Deadline) obj;
            return other.getDescription().equals(this.getDescription())
                    && other.date.equals(this.date);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getDescription().hashCode() + this.date.hashCode();
    }
}