import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")) + ")";
    }

    @Override
    public String addToFile() {
        return "E | " + super.addToFile() + " | " + from.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm")) +
                " | " + to.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm")) + "\n";
    }

}
