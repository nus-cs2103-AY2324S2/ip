import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " + description + " "
                + getFormattedDateRange();
    }

    @Override
    protected String getType() {
        return "E";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    protected String getFormattedDateRange() {
        return from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")) +
                " to " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"));
    }
}
