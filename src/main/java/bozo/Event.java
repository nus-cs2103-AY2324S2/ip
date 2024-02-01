package bozo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;
    protected static DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, input);
        this.to = LocalDateTime.parse(to, input);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from.format(output), to.format(output));
    }

    @Override
    public String save() {
        return "E " + super.save() + String.format(" | %s | %s", from.format(input), to.format(input));
    }
 }
