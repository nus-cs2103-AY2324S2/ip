package lindi.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


/**
 * Represents an event task
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Initialises the event task
     *
     * @param description description of the event
     * @param from start datetime of event following '{@value SAVE_LOAD_DT_FORMAT_STRING}' format
     * @param to end datetime of the dateline following '{@value SAVE_LOAD_DT_FORMAT_STRING}' format
     * @throws CreateEventException if param from or to provided is in the wrong format
     */
    public Event(String description, String from, String to) throws CreateEventException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, Task.SAVE_LOAD_DATETIME_FORMAT);
            this.to = LocalDateTime.parse(to, Task.SAVE_LOAD_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new CreateEventException(String.format("Event /from or /to argument in the wrong format. Use "
                    + "format '%s' for each instead. Not saving seconds and below :)",
                    Task.SAVE_LOAD_DT_FORMAT_STRING));
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (when: %s - %s)",
                this.from.format(Task.DISPLAY_DATETIME_FORMAT),
                        this.to.format(Task.DISPLAY_DATETIME_FORMAT));
    }

    @Override
    public String toParsedFormat() {
        return String.format("E | %c | %s | %s | %s",
                this.isDone ? 'y' : 'n', this.description,
                        this.from.format(Task.SAVE_LOAD_DATETIME_FORMAT),
                                this.to.format(Task.SAVE_LOAD_DATETIME_FORMAT));
    }
}
