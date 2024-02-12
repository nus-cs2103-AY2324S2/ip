package bob.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import bob.Storage;
import bob.Ui;

public class Deadline extends Task {
    public static final String STORAGE_INDICATOR = "D";

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) throws DateTimeParseException {
        super(description);
        this.by = by;
    }

    @Override
    public String toStorageFormat() {
        return STORAGE_INDICATOR + " | " + super.toStorageFormat() + " | "
                + Storage.formatDateTime(by);
    }

    @Override
    public boolean isOccurringOn(LocalDate date) {
        return by.toLocalDate().equals(date);
    }

    @Override
    public boolean isDueIn(int days) {
        boolean isUpcoming = by.isAfter(LocalDateTime.now());
        boolean isWithin = ChronoUnit.DAYS.between(LocalDateTime.now(), by) <= days;
        return isUpcoming && isWithin;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Ui.formatDateTime(by) + ")";
    }
}
