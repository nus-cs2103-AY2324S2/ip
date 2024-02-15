package bob.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import bob.Storage;
import bob.Ui;

public class Event extends Task {
    public static final String STORAGE_INDICATOR = "E";

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) throws DateTimeParseException {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getStorageFormat() {
        return STORAGE_INDICATOR + " | " + super.getStorageFormat() + " | "
                + Storage.formatDateTime(from) + " | " + Storage.formatDateTime(to);
    }

    @Override
    public boolean isOccurringOn(LocalDate date) {
        return !(date.isBefore(from.toLocalDate()) || date.isAfter(to.toLocalDate()));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Ui.formatDateTime(from)
                + " to: " + Ui.formatDateTime(to) + ')';
    }
}
