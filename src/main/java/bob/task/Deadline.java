package bob.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import bob.Storage;
import bob.Ui;

/**
 * Represents a deadline with a specified description and due time. A <code>Deadline</code> object corresponds to
 * a task with a description and a due time.
 */
public class Deadline extends Task {
    public static final String STORAGE_INDICATOR = "D";

    protected LocalDateTime by;

    /**
     * Returns a deadline with a specified description and due time.
     *
     * @param description The description for the deadline.
     * @param by The due time for the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns storage format of the deadline.
     *
     * @return The string representation of the deadline in storage.
     */
    @Override
    public String getStorageFormat() {
        return STORAGE_INDICATOR + " | " + super.getStorageFormat() + " | "
                + Storage.formatDateTime(by);
    }

    /**
     * Returns whether the deadline is occurring on a specified day.
     *
     * @param date The date on which the deadline is said to occur on.
     * @return Whether the deadline is due on the specified date.
     */
    @Override
    public boolean isOccurringOn(LocalDate date) {
        return by.toLocalDate().equals(date);
    }

    /**
     * Returns whether the deadline is due in a specified number of days.
     *
     * @param days The number of days in which the deadline is said to be due.
     * @return Whether the deadline is due in the specified number of days.
     */
    @Override
    public boolean isDueIn(int days) {
        boolean isUpcoming = by.isAfter(LocalDateTime.now());
        boolean isWithin = ChronoUnit.DAYS.between(LocalDateTime.now(), by) <= days;
        return isUpcoming && isWithin;
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return The string representation of the deadline to be displayed to the user.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Ui.formatDateTime(by) + ")";
    }
}
