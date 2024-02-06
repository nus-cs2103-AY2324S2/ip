import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime from, to;

    public Event(String name, String from, String to) throws GulieException {
        this(name, from, to, false);
    }

    public Event(String name, String from, String to, boolean mark) throws GulieException {
        super(name, mark);
        try {
            this.from = LocalDateTime.parse(from);
            this.to = LocalDateTime.parse(to);
        } catch (DateTimeParseException e) {
            throw new GulieException("The datetime that you have given is invalid.");
        }
    }

    @Override
    public String toSaveString() {
        return String.format("E\t%s\t%s\t%s", super.toSaveString(), from, to);
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = Gulie.getDateTimeFormatter();
        return String.format("[E]%s (from: %s to: %s)", super.toString(), dtf.format(from), dtf.format(to));
    }
}
