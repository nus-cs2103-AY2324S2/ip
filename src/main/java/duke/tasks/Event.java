package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private final LocalDateTime from;

    private final LocalDateTime to;

    public Event(String name, String from, String to) throws DateTimeParseException {
        super(name);
        assert from != null && to != null : "from and to cannot be null";
        assert !from.contains("\n") && !to.contains("\n") : "from and to cannot contain newlines";
        this.from = LocalDateTime.parse(from, Task.INPUT_TIME_FORMATTER);
        this.to = LocalDateTime.parse(to, Task.INPUT_TIME_FORMATTER);
    }

    @Override
    public String describe() {
        return String.format("[E]%s (from: %s to: %s)",
                super.describe(),
                this.from.format(Task.OUTPUT_TIME_FORMATTER),
                this.to.format(Task.OUTPUT_TIME_FORMATTER));
    }

    @Override
    public String toStorageString() {
        return String.format("E,%s,%s,%s", super.toStorageString(),
                this.from.format(INPUT_TIME_FORMATTER),
                this.to.format(INPUT_TIME_FORMATTER));
    }
}
