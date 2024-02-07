package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

/**
 * The Event class extends the Task class and represents an event with a start and end time.
 */
public class Event extends Task implements Serializable {
    private LocalDateTime start;
    private LocalDateTime end;
    private static final long serialVersionUID = 4L;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + (isDone ? "X" : " ") + "] " + description
                + "(from: " + start.format(DateTimeFormatter.ofPattern("HH:mm MMM dd YYYY"))
                + " to: "+ end.format(DateTimeFormatter.ofPattern("HH:mm MMM dd YYYY")) + ")";
    }
}
