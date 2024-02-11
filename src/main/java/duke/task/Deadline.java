package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 *
 * <p>The {@code Deadline} class extends the {@link Task} class and represents a task with a
 * specific deadline. It contains methods to retrieve the deadline and convert the task to its
 * string representation for storage.</p>
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs a deadline task with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadline    The deadline of the task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description, TaskType.Deadline);
        this.deadline = deadline;
    }

    @Override
    public String storageString() {
        // Format the task status, task information, and deadline into a single string
        String isCompleted = this.isDone() ? "[X]" : "[ ]";
        return String.format("[D] | %s | %s | %s", isCompleted, this.getDescription().trim(),
                this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    public LocalDateTime getBy() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }
}
