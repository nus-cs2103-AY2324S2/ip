package iggly.model;

import static iggly.util.DateTimeUtil.formatDate;

import java.time.LocalDate;

/**
 * The {@link Schedule} class represents a task with a specific date range.
 */
public class Schedule extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs an {@link Schedule} instance with the specified title, start time, and end time.
     *
     * @param title The title of the schedule task.
     * @param from The start date of the schedule.
     * @param to The end date of the schedule.
     */
    public Schedule(String title, LocalDate from, LocalDate to) {
        super(title);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the schedule task, including its type, title, and schedule details.
     *
     * @return A formatted string representing the schedule task.
     */
    @Override
    public String toString() {
        return "[S]"
                + super.toString()
                + " (from: " + formatDate(from)
                + " to: " + formatDate(to) + ")";
    }
}
