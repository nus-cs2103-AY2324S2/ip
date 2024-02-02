package felix.task;

import java.time.LocalDateTime;

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
        this.start = LocalDateTime.parse(start, inputFormatter);
        this.end = LocalDateTime.parse(end, inputFormatter);
    }

    /**
     * Returns the String representation of the Deadline instance to be written to file.
     */
    @Override
    public String toFileString() {
        return String.format("E | %s | %s | %s | %s", this.getStatusIcon(), this.getDescription(),
                this.start.format(inputFormatter), this.end.format(inputFormatter));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.start.format(outputFormatter), this.end.format(outputFormatter));
    }
}
