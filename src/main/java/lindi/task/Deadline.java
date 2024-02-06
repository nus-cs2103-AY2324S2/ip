package lindi.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime by;
    public Deadline(String description, String by) throws CreateDeadlineException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, this.saveLoadDateTimeFormat);
        } catch (DateTimeParseException e) {
            throw new CreateDeadlineException(String.format("lindi.task.Deadline /by argument in the wrong format. Use " +
                    "format '%s' instead. Not saving seconds and below :)", this.saveLoadDtFormatString));
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(this.displayDateTimeFormat) + ")";
    }

    @Override
    public String parsedFormatToSave() {
        return String.format("D | %c | %s | %s",
                this.isDone ? 'y' : 'n', this.description,
                this.by.format(this.saveLoadDateTimeFormat));
    }
}
