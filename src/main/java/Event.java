import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalTime to;

    public Event (String description, String from, String to) throws IllegalArgumentException {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        this.to = LocalTime.parse(to, DateTimeFormatter.ofPattern("HHmm"));
    }

    public Event (String description, boolean done, String from, String to) {
        super(description);
        super.updateIsDone(done);
        System.out.println(from);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.to = LocalTime.parse(to, DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String getSaveTask() {
        return "E | " + super.getSaveTask() + " | "
                + from.toString().replace("T", " ") + " | " + to;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] "
                + this.description + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")) + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }
}