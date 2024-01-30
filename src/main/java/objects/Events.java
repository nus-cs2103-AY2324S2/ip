package objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task implements Serializable {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Events(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from.format(formatter), this.to.format(formatter));
    }
}
