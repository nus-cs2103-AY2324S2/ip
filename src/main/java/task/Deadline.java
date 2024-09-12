package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * {@link Task} that contains a {@link java.time.LocalDate} date to complete the task by.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Initialises a Deadline task with a description and deadline date.
     * Is marked as not completed by default.
     *
     * @param desc Description of the task to be completed.
     * @param by Date of when to complete the task by.
     */
    public Deadline(String desc, LocalDate by) {
        super(desc);
        this.by = by;
    }

    /**
     * Initialises a Deadline task with the task completion status, a description and deadline date.
     * Use to manually set the completion status of the task when initialising.
     *
     * @param isDone Completion status of the task.
     * @param desc Description of the task to be completed.
     * @param by Date of when to complete the task by.
     */
    public Deadline(boolean isDone, String desc, LocalDate by) {
        super(isDone, desc);
        this.by = by;
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                getStatusIcon(),
                getDescription(),
                by.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}
