package lindi.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, String from, String to) throws CreateEventException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, this.saveLoadDateTimeFormat);
            this.to = LocalDateTime.parse(to, this.saveLoadDateTimeFormat);
        } catch (DateTimeParseException e) {
            throw new CreateEventException(String.format("lindi.task.Event /from or /to argument in the wrong format. Use "
                    + "format '%s' for each instead. Not saving seconds and below :)", this.saveLoadDtFormatString));
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (when: %s - %s)",
                this.from.format(this.displayDateTimeFormat),
                        this.to.format(this.displayDateTimeFormat));
    }

    @Override
    public String parsedFormatToSave() {
        return String.format("E | %c | %s | %s | %s",
                this.isDone ? 'y' : 'n', this.description,
                        this.from.format(this.saveLoadDateTimeFormat),
                                this.to.format(this.saveLoadDateTimeFormat));
    }
}
