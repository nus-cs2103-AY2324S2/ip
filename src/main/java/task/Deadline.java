package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * The Deadline class represents a task with a specific end date.
 * It extends the Task class and includes methods to query by date and provide a formatted string representation.
 */
public class Deadline extends Task {

    /** The end date of the deadline. */
    private LocalDate endDate;

    /**
     * Constructs a Deadline task with the specified name and end date.
     * @param name The name of the deadline.
     * @param endDate The end date of the deadline.
     */
    public Deadline(String name, LocalDate endDate) {
        super(name);
        this.endDate = endDate;
    }

    /**
     * Queries whether the deadline occurs on the specified date.
     * @param date The date to be queried.
     * @return true if the deadline occurs on the specified date, false otherwise.
     */
    @Override
    public boolean queryByDate(LocalDate date) {
        return date.isEqual(this.endDate);
    }

    /**
     * Provides a formatted string representation of the Deadline task.
     * @return A string representing the Deadline task in a readable format.
     */
    @Override
    public String toString() {
        String by = this.endDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        return String.format("[D]%s %s (by: %s)", (super.isMarked ? "[X]" : "[ ]"), super.name, by);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline deadline = (Deadline) obj;
            return this.name.equals(deadline.name) && this.endDate.equals(deadline.endDate);
        }
        return false;
    }

    @Override
    public int hashCode() {
        String hashString = String.format("deadline,%s,%s", name, endDate.toString());
        return hashString.hashCode();
    }
}
