import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected DateTask from;
    protected DateTask to;

    public Event(String description, String from, String to) {
        this(description, from, to , false);
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime parseDateTimeFrom = LocalDateTime.parse(from, formatter);
        LocalDateTime parseDateTimeTo = LocalDateTime.parse(to, formatter);
        this.from = new DateTask(parseDateTimeFrom);
        this.to = new DateTask(parseDateTimeTo);
    }

    @Override
    public String saveFormat() {
        return "[E]" + super.toString() + 
                String.format(" (from: %s to: %s)", from.saveFormat(), to.saveFormat());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + 
        String.format(" (from: %s to: %s)", from, to);
    }
}
