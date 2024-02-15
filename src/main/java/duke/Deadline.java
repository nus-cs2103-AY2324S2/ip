package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadlines class represents a task with a deadline.
 * It extends the Task class and adds functionality to store and display the deadline of the task.
 * Deadline is a subclass of {@link Task}, inheriting its properties and methods
 */
public class Deadline extends Task {

    /** The deadline of the task. */
    public LocalDateTime deadline;

    /**
     * Constructs a Deadlines object with the specified description and deadline.
     *
     * @param description a String representing the description of the task
     * @param deadline    a LocalDateTime representing the deadline of the task
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDate = deadline.format(outputFormatter);
        return String.format("[D]%s (by: %s)", super.toString(), formattedDate);
    }
}
