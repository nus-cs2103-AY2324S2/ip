package task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a Task that has description and deadline.
 */
public class Deadline extends Task {

    private LocalDate date;
    private LocalTime time;

    /**
     * The constructor of Deadline.
     *
     * @param description Description of the task.
     * @param date Date of the deadline.
     * @param time Time of the deadline.
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " " + time + ")";
    }
}
