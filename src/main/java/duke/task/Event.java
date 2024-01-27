package duke.task;

import java.time.LocalDateTime;
import java.util.Map;

public class Event extends Task {
    private LocalDateTime from, to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(Map<String, String> components) throws InvalidComponents {
        super(components.get("DESCRIPTION"));
        validateComponentKeys(keys("/from", "/to"), components.keySet());
        from = parseDateTime(components.get("/from"));
        to = parseDateTime(components.get("/to"));
    }

    @Override
    public String describe() {
        return super.describe() + "; starts from "
                + from.format(describeTimeFormat) + " and ends at " + to.format(describeTimeFormat);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }
}
