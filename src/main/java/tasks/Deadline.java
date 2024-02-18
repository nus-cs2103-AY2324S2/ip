package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that have a deadline.
 */
public class Deadline extends Task {
    private LocalDate date;

    /**
     * Creates a Deadline object.
     *
     * @param task Indicates what the task is about.
     * @param date Indicates when the task should be done by.
     */
    public Deadline(String task, String date) {
        super(task);
        this.date = LocalDate.parse(date);
    }

    /**
     * Creates a Deadline object.
     *
     * @param task Indicates what the task is about.
     * @param date Indicates when the task should be done by.
     * @param isComplete Indicates whether the task has been completed or not.
     */

    public Deadline(String task, String dateOfReminder, String date, boolean isComplete) {
        super(task, dateOfReminder, isComplete);
        this.date = LocalDate.parse(date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String stringify() {
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.stringify() + " (by: " + formattedDate + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + this.date;
    }

}
