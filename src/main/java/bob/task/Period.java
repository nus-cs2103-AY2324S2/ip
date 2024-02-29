package bob.task;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import bob.Storage;
import bob.Ui;

/**
 * Represents a task that need to be done within a certain period.
 * A <code>Period</code> object corresponds to a task to be done from a certain start date to end date.
 */
public class Period extends Task {
    public static final String STORAGE_INDICATOR = "P";

    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Returns a task with a specified description, start time and end time.
     *
     * @param description The description for the task.
     * @param start The start time for the task.
     * @param end The end time for the task.
     */
    public Period(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns storage format of the task.
     *
     * @return The string representation of the task in storage.
     */
    @Override
    public String getStorageFormat() {
        return STORAGE_INDICATOR + " | " + super.getStorageFormat() + " | "
                + Storage.formatDateTime(start) + " | " + Storage.formatDateTime(end);
    }

    /**
     * Returns whether the task is due in a specified number of days.
     *
     * @param days The number of days in which the task is said to be due.
     * @return Whether the task is due in the specified number of days.
     */
    @Override
    public boolean isDueIn(int days) {
        boolean hasStarted = start.isBefore(LocalDateTime.now());
        boolean isUpcoming = end.isAfter(LocalDateTime.now());
        boolean isWithin = ChronoUnit.DAYS.between(LocalDateTime.now(), end) <= days;
        return hasStarted && isUpcoming && isWithin && !isDone;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task to be displayed to the user.
     */
    @Override
    public String toString() {
        return "[P]" + super.toString() + " (start: " + Ui.formatDateTime(start)
                + " end: " + Ui.formatDateTime(end) + ')';
    }
}
