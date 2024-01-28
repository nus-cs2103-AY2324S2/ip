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
}
