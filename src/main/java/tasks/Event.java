package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy ha");

    public Event(String desc, String from, String to) {
        super(desc);
        this.from = LocalDateTime.parse(from, inputFormatter);
        this.to = LocalDateTime.parse(to, inputFormatter);
    }    

    @Override
    public String toString() {
        return String.format("[Event]%s (from: %s to: %s)", super.toString(), this.from.format(outputFormatter), this.to.format(outputFormatter));
    }

    @Override
    public String fileString() {
        return String.format("EVENT | %s | %s | %s", super.fileString(), this.from.format(inputFormatter), this.to.format(inputFormatter));
    }

}
