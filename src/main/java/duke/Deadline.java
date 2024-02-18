package duke;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;
    protected Timer timer;

    /**
     * Initializes a Deadline object with the given description and deadline.
     *
     * @param description Description of the task.
     * @param by Deadline of the task in LocalDate format.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        assert by != null : "Deadline should not be null";
        this.by = by;
        timer = new Timer();
        scheduleReminder(description, by);
    }

    private void scheduleReminder(String description, LocalDateTime by) {
        timer.schedule(new Reminder("You have a deadline for " + description + " on "
            + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))),
            Date.from(by.atZone(ZoneId.systemDefault()).toInstant()));
    }

    /**
     * Returns the type of the task.
     *
     * @return task type
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns the date of the task in LocalDate object.
     *
     * @return date of the task
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns all the information of the task in String format.
     *
     * @return date of the task in String format
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
    }
}
