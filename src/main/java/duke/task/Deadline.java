package duke.task;

import java.time.LocalDateTime;
import java.util.Map;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

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
