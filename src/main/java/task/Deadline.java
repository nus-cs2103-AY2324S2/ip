package task;
import java.time.LocalDateTime;

import util.CsvUtil;
import util.DateTimeUtil;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline object with the given description and deadline.
     *
     * @param description the description of the deadline
     * @param by the deadline date and time
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline object with the given marking status, description, and deadline.
     *
     * @param isMarked the marking status of the deadline
     * @param description the description of the deadline
     * @param by the deadline date and time
     */
    public Deadline(boolean isMarked, String description, LocalDateTime by) {
        super(isMarked, description);
        this.by = by;
    }

    /**
     * Formats the Deadline object into a CsvUtil object for CSV file storage.
     *
     * @return the CsvUtil object representing the Deadline
     */
    @Override
    public CsvUtil format() {
        return new CsvUtil("D", String.valueOf(super.isMarked), super.description,
            DateTimeUtil.format(by));
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return a string representation of the Deadline
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
            DateTimeUtil.format(by));
    }

    /**
     * Checks if the Deadline object is equal to another object.
     *
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Deadline d = (Deadline) o;
        return isMarked == d.isMarked && description.equals(d.description) && d.by.equals(by);
    }
}
