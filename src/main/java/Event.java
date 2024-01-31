import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Creates a Event task with description, start time and end time.
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
}
