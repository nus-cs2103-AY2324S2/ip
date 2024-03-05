package quacky;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in the Quacky application. A deadline task is a task that needs
 * to be completed before a specific date.
 */
public class Deadline extends Task{
    protected LocalDate byDate;
    protected String stringByDate;

    /**
     * Constructs a new Deadline task with the specified description and deadline date.
     *
     * @param description The text description of the deadline task.
     * @param byDate The date the task needs to be completed by.
     */
    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;
        this.stringByDate = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.stringByDate + ")";
    }

    @Override
    protected String toFileString() {
        return "D | " + super.toFileString() + " | " + this.byDate;
    }

    @Override
    public boolean clashesWith(Task other) {
        if (other instanceof Deadline) {
            Deadline otherDeadline = (Deadline) other;
            return this.byDate.equals(otherDeadline.byDate);
        }
        return false;
    }
}
