import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{

    private LocalDateTime from;
    private LocalDateTime to;
    private static DateTimeFormatter ioFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd LLL yyyy HHmm");

    public Event(String description, String from, String to, boolean isDone) throws DateTimeParseException {
        super(description, isDone);
        this.from = LocalDateTime.parse(from, ioFormatter);
        this.to = LocalDateTime.parse(to, ioFormatter);;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), this.from.format(displayFormatter), this.to.format(displayFormatter));
    }

    @Override
    public String formatData() {
        return "E" + " | " + this.formatTask() + " | " + this.from.format(ioFormatter) + " | "
                + this.to.format(ioFormatter);
    }

    public static void setDisplayFormatter(String pattern) {
        Event.displayFormatter = DateTimeFormatter.ofPattern(pattern);
    }
}
