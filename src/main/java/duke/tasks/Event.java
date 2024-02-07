package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class Event extends Task implements Serializable {
    private final LocalDateTime start;
    private final LocalDateTime end;
    private static final long serialVersionUID = 4L;


    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String toString() {
        return "[E]" + "[" + (getIsDone() ? "X" : " ") + "] " + getDescription()
                + "(from: " + start.format(DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy"))
                + " to: "+ end.format(DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy")) + ")";
    }
}
