package xilef.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * A {@code Deadline} is a type of {@code Task} with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    /**
     * Creates a new {@code Deadline} with the given description and deadline.
     *
     * @param description The description of the {@code Deadline}.
     * @param deadline The deadline of the {@code Deadline}.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy| hh:mm a")) + ")";
    }

    @Override
    public String toStringForFile() {
        return "D | " + super.toStringForFile() + " | " + this.deadline;
    }
}