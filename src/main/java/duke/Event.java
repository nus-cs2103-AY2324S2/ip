package duke;

import java.time.LocalDateTime;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected LocalDateTime from, to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, Constants.INPUT_FORMATTER);
        this.to = LocalDateTime.parse(to, Constants.INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(Constants.OUTPUT_FORMATTER)
            + " to: " + to.format(Constants.OUTPUT_FORMATTER) + ")";
    }

    @Override
    public String serializeToCommand(int taskIndex) {
        return "event " + description + " /from " + from.format(Constants.INPUT_FORMATTER)
            + " /to " + to.format(Constants.INPUT_FORMATTER) + "\n" + serializeDoneMark(taskIndex);
    }
}
