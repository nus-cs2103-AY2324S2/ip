import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

    public Event(String taskName, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(taskName);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Event(String taskName, LocalDateTime startDateTime, LocalDateTime endDateTime, boolean isDone) {
        super(taskName, isDone);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "[E] " + super.taskTitle + " (from: "
                + this.startDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")) + " to: "
                + this.endDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")) + ")"
                + super.getStatusIcon();
    }

    @Override
    public String toFile() {
        return "E " + super.toFile() + " | "
                + this.startDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm")) + "-"
                + this.endDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm")) + "\n";
    }
}
