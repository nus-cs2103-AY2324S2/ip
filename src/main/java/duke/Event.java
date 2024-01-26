package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    /**
     * Creates an event task.
     * 
     * @param description Description of the task.
     * @param fromDate Start date of the task.
     * @param toDate End date of the task.
     * @throws DukeException If the start or end date is not in the correct format.
     */
    public Event(String description, String fromDate, String toDate) throws DukeException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.fromDate = LocalDateTime.parse(fromDate, formatter);
            this.toDate = LocalDateTime.parse(toDate, formatter);
        } catch (Exception e) {
            throw new DukeException("Please enter a valid date and time in the format yyyy-MM-dd HH:mm");
        }
    }

    @Override
    public String toFileLine() {
        String fileLine = super.toFileLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("E | %s | %s | %s", fileLine.substring(4),
                this.fromDate.format(formatter), this.toDate.format(formatter));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.fromDate.format(formatter), this.toDate.format(formatter));
    }
}
