package felix.task;

import java.time.LocalDateTime;

/**
 * Class representing a task with a start and end time
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructor for Event class.
     *
     * @param description Description of task.
     * @param start Date and time when task begins
     * @param end Date and time when task ends
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDateTime.parse(start, INPUT_FORMATTER);
        this.end = LocalDateTime.parse(end, INPUT_FORMATTER);
    }

    /**
     * Returns the String representation of the Deadline instance to be written to file.
     */
    @Override
    public String toFileString() {
        return String.format("E | %s | %s | %s | %s", this.getStatusIcon(), this.getDescription(),
                this.start.format(INPUT_FORMATTER), this.end.format(INPUT_FORMATTER));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.start.format(OUTPUT_FORMATTER), this.end.format(OUTPUT_FORMATTER));
    }
}
