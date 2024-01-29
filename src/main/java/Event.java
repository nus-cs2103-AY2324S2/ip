import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(boolean done, String name, String from, String to) {
        super(done, name);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }
    public Event(String name, String from, String to) {
        super(name);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)\n"
                , this.done ? "X" : " "
                , this.name
                , this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                , this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
