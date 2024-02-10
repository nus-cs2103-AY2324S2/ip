package cat.task;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * A task with a deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Construct a task with a deadline from parsed components. The expected components are DESCRIPTION and /by.
     *
     * @throws InvalidComponents when the parsed components don't match the expected components
     */
    public Deadline(Map<String, String> components) throws InvalidComponents {
        super(components.get("DESCRIPTION"));
        validateComponentKeys(keys("/by"), components.keySet());
        by = parseDateTime(components.get("/by"));
    }

    @Override
    public String describe() {
        return super.describe() + "; due by " + by.format(describeTimeFormat);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
