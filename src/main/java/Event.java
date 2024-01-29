import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String dateStart;
    protected String dateEnd;

    public Event(String description, String dateStart, String dateEnd) {
        super(description);
        this.dateStart = LocalDateTime.parse(dateStart, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        this.dateEnd = LocalDateTime.parse(dateEnd, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[" + super.getStatusIcon() + "]" + " Event | "
                + this.description + " | "
                + this.dateStart
                + " - " + this.dateEnd;
    }
}
