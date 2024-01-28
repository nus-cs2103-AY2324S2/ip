package task;
import java.time.LocalDateTime;

import util.CsvUtil;
import util.DateTimeUtil;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(boolean isMarked, String description, LocalDateTime from, LocalDateTime to) {
        super(isMarked, description);
        this.from = from;
        this.to = to;
    }

    @Override
    public CsvUtil format() {
        return new CsvUtil("E", String.valueOf(super.isMarked), super.description,
            DateTimeUtil.format(from),
            DateTimeUtil.format(to));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
            DateTimeUtil.format(from),
            DateTimeUtil.format(to));
    }
    @Override
    public boolean equals(Object o) {
        // Step 1: Check if the objects are the same instance
        if (this == o) {
            return true;
        }

        // Step 2: Check if the object is null or of a different class
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        // Step 3: Convert the object to a ToDo instance
        Event e = (Event) o;

        // Step 4: Compare the individual fields for equality
        return isMarked == e.isMarked && description.equals(e.description)
                && e.from.equals(from) && e.to.equals(to);
    }
}
