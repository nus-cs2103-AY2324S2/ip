package tes.taskmanager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by; // the deadline assigned

    /**
     * Constructs the task with deadline.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String by1 = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        return "D" + super.toString() + " | by: " + by1;
    }
}
