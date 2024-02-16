package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with start and end timings.
 *
 * <p>The {@code Event} class extends the {@link Task} class and represents an event task with
 * specific start and end timings. It contains methods to retrieve the start and end timings and
 * convert the task to its string representation for storage.</p>
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an event task with the given description, start, and end timings.
     *
     * @param description The description of the event task.
     * @param from        The start timing of the event.
     * @param to          The end timing of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskType.Event);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an event task with status isDone.
     */
    public Event(String description, LocalDateTime from,
        LocalDateTime to, boolean isDone) {
        super(description, TaskType.Event);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    @Override
    public String storageString() {
        // Format the task status, task information, from, and end into a single string
        String isCompleted = this.isDone() ? "[X]" : "[ ]";
        return String.format("[E] | %s | %s | %s | %s", isCompleted, this.getDescription().trim(),
                this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")),
                this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }
}
