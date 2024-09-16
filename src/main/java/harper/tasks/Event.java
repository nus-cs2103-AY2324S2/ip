package harper.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Creates a task.Event task with description, start time and end time.
     *
     * @param description Description of the task.
     * @param start Start time of the task.
     * @param end End time of the task.
     */
    public Event(String description, boolean isDone, LocalDateTime start, LocalDateTime end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
        return "E" + super.toString() + " | "
                + this.start.format(dateTimeFormatter) + " - " + this.end.format(dateTimeFormatter);
    }

    /**
     * Updates start date time.
     *
     * @param start New start date time.
     */
    public void updateStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * Updates end date time.
     *
     * @param end New end date time.
     */
    public void updateEnd(LocalDateTime end) {
        this.end = end;
    }
}
