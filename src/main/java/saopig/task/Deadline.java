package saopig.task;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;


public class Deadline extends Task {

    private LocalDateTime by;
    private ZoneId userZoneId = ZoneId.systemDefault();
    private ZonedDateTime byZonedDateTime;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        this.byZonedDateTime = by.atZone(userZoneId);
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)
                .withLocale(Locale.ENGLISH);
        return "[D]" + super.toString() + " (by: " + formatter.format(byZonedDateTime) + ")";
    }
}