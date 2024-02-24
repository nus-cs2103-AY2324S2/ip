package sam.task;

import sam.SamException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Event(String description, String from, String to) throws SamException {
        super(description);

        if (from.isBlank()) {
            throw new SamException("Please provide a starting time.");
        } else if (to.isBlank()) {
            throw new SamException("Please provide an ending time.");
        }

        this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
        this.to = LocalDateTime.parse(to, INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s", super.toString(), from.format(OUTPUT_FORMATTER),
                to.format(OUTPUT_FORMATTER));
    }

    @Override
    public String toFileFormat() {
        return String.format("E | %d | %s | %s to %s", isDone ? 1 : 0, this.description, from.format(INPUT_FORMATTER),
                to.format(INPUT_FORMATTER));
    }
}