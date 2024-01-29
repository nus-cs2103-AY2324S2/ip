import java.time.LocalDateTime;

public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + super.formatOutputDate(from) +
                " to: " + super.formatOutputDate(to) + ")";
    }

    @Override
    public String addToFile() {
        return "E | " + super.addToFile() + " | " + super.formatInputDate(from) +
                " | " + super.formatInputDate(to) + "\n";
    }

}
