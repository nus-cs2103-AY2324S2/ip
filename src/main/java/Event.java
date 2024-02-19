import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private static final String PATTERN = "dd MMM yyyy hh:mm a";

    public Event(String description, boolean isDone, LocalDateTime startTime, LocalDateTime endTime) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (start time: " + this.startTime.format(DateTimeFormatter.ofPattern(PATTERN))
                + ")"
                + " (end time: " + this.endTime.format(DateTimeFormatter.ofPattern(PATTERN))
                + ")";
    }
    @Override
    public String toFileString() {
        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "E|"
                + super.toFileString()
                + "|"
                + this.startTime.format(fileFormatter)
                + " to "
                + this.endTime.format(fileFormatter);
    }
}

// // E|1|description|yyyy-MM-dd_HH:mm_to_yyyy-MM-dd_HH:mm