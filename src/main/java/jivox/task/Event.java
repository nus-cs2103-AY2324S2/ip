package jivox.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime from;

    private LocalDateTime to;
    public Event(String content, LocalDateTime from, LocalDateTime to) {
        super(content);
        this.from = from;
        this.to = to;
    }


    public String getType() {
        return "E";
    }

    public String getStart() {
        return this.from.toString();
    }

    public LocalDateTime getDeadline() {
        return this.to;
    }

    public String saveFormat() {
        return this.getType() + " | " + (this.getStatus() ? "1" : "0")
                + " | " + this.getDescription() + " | "
                + this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                + " to " + this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
    }
}
