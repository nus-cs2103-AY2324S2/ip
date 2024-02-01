package jmsandiegoo.tyrone.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

public class DateTime {
    private final DateTimeFormatter inputFormatter;
    private final DateTimeFormatter outputFormatter;
    private final TemporalAccessor dateTime;

    public DateTime(String dateTimeStr) throws DateTimeParseException {
        this.inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd[ HH:mm]");
        this.outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy[ hh:mma]");
        this.dateTime = this.parseDateTimeString(dateTimeStr);
    }

    private TemporalAccessor parseDateTimeString(String dateTimeStr) throws DateTimeParseException {
        return this.inputFormatter.parseBest(dateTimeStr, LocalDateTime::from, LocalDate::from);
    }

    public String formatDateTime() {
        return this.outputFormatter.format(this.dateTime);
    }

    public String serializeDateTime() {
        return this.inputFormatter.format(this.dateTime);
    }
}
