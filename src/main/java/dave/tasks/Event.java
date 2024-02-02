package dave.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    static final DateTimeFormatter FORMATTER_INPUT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    static final DateTimeFormatter FORMATTER_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy ha");

    public Event(String desc, String from, String to) {
        super(desc);
        this.from = LocalDateTime.parse(from, FORMATTER_INPUT);
        this.to = LocalDateTime.parse(to, FORMATTER_INPUT);
    }    

    @Override
    public String toString() {
        return String.format("[Event]%s (from: %s to: %s)", super.toString(), this.from.format(FORMATTER_OUTPUT), this.to.format(FORMATTER_OUTPUT));
    }

    @Override
    public String fileString() {
        return String.format("EVENT | %s | %s | %s", super.fileString(), this.from.format(FORMATTER_INPUT), this.to.format(FORMATTER_INPUT));
    }

}
