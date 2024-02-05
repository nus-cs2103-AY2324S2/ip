package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Events(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: "+ to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
