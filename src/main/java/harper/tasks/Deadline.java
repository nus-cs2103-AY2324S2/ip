package harper.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Creates a task.Deadline task with description and deadline.
     *
     * @param description The description of the task.Deadline task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
        return "D" + super.toString() + " | " + this.by.format(dateTimeFormatter);
    }

    /**
     * Updates deadline.
     *
     * @param by New deadline.
     */
    public void updateBy(LocalDateTime by) {
        this.by = by;
    }
}
