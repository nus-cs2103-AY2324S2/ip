package earl.tasks;

import java.time.LocalDateTime;

import earl.exceptions.TimeException;
import earl.util.parsers.DateTimeParser;

/**
 * Class representing task of type event.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Class constructor.
     *
     * @param description  the task description
     * @param from         the date and time of the start
     * @param to           the date and time of the end
     */
    public Event(String description, String from, String to)
            throws TimeException {
        super(description);
        taskType = TaskType.EVENT;
        this.from = DateTimeParser.parse(from);
        this.to = DateTimeParser.parse(to);
        if (this.from.isAfter(this.to) || this.from.isEqual(this.to)) {
            throw new TimeException();
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                DateTimeParser.dateTimeToString(from),
                DateTimeParser.dateTimeToString(to));
    }

    @Override
    public String toStorageString() {
        return String.format("%s,%s,%s,%s,%s",
                taskType.toString(),
                super.getStatusIcon(),
                getDescription(),
                DateTimeParser.dateTimeToString(from),
                DateTimeParser.dateTimeToString(to));
    }
}
