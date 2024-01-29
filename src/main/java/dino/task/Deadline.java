package dino.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Represents a Task with a single deadline. */
public class Deadline extends Task {
    private LocalDateTime deadlineTime;

    /**
     * Constructs a new Deadline task with the given description and deadline time.
     *
     * @param description   The description of the task.
     * @param deadlineTime  The deadline time of the task.
     */
    public Deadline(String description, LocalDateTime deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    /**
     * Returns the deadline time of the task.
     *
     * @return The deadline time.
     */
    public LocalDateTime getDateTime() {
        return deadlineTime;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A formatted string representing the task's status, description, and deadline time.
     */
    public String toString() {
        return String.format(" D | %s | %s | by: %s",
                getStatusIcon(),
                description,
                deadlineTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }
}

