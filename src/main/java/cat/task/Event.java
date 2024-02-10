package cat.task;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * A task that has a start and an end.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Construct a task with a start and an end from parsed components.
     * The expected components are /from and /to.
     * @throws InvalidComponents when the parsed components don't match the expected components
     */
    public Event(String description, Map<String, String> components) throws InvalidComponents {
        super(description);
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
