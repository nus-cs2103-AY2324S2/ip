package dino.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The `Deadline` class is a subclass of `Task` that represents a task with a specific deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;


    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public LocalDateTime getDate() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + (isDone() ? "X" : " ") + "] " + getDescription() + "(by: "
                + deadline.format(DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy")) + ")";
    }
}
