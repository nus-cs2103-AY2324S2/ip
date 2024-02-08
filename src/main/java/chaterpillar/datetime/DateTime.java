package chaterpillar.datetime;

import chaterpillar.exceptions.ChaterpillarException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

public class DateTime {
    public LocalDateTime dateTime;
    public Boolean dateOnly;
    public Boolean timeOnly;
    public DateTime(String dt) throws ChaterpillarException {
        this.dateOnly = false;
        this.timeOnly = false;
        this.dateTime = parseDateTime(dt);
    }

    public DateTime(LocalDate date) throws ChaterpillarException {
        this.dateOnly = false;
        this.timeOnly = false;
        this.dateTime = date.atTime(0, 0);
    }

    /**
     * Parses the string of date and/or time to a
     * <code>LocalDateTime</code> object.
     * @param str <code>String</code> of date and/or time
     * @return <code>LocalDateTime</code> object
     */
    public LocalDateTime parseDateTime(String str) throws ChaterpillarException {
        DateTimeFormatterBuilder dateTimeFormatterBuilder =
                new DateTimeFormatterBuilder()
                        .appendPattern("[d/M/uuuu HHmm]")
                        .appendPattern("[d-M-uuuu HHmm]")
                        .appendPattern("[d M uuuu HHmm]")
                        .appendPattern("[d/MMM/uuuu HHmm]")
                        .appendPattern("[d-MMM-uuuu HHmm]")
                        .appendPattern("[d MMM uuuu HHmm]")
                        .appendPattern("[d/MMMM/uuuu HHmm]")
                        .appendPattern("[d-MMMM-uuuu HHmm]")
                        .appendPattern("[d MMMM uuuu HHmm]")
                        .appendPattern("[d/M HHmm]")
                        .appendPattern("[d-M HHmm]")
                        .appendPattern("[d M HHmm]")
                        .appendPattern("[d/MMM HHmm]")
                        .appendPattern("[d-MMM HHmm]")
                        .appendPattern("[d MMM HHmm]")
                        .appendPattern("[d/MMMM HHmm]")
                        .appendPattern("[d-MMMM HHmm]")
                        .appendPattern("[d MMMM HHmm]")

                        .appendPattern("[d/M/uuuu h:mm a]")
                        .appendPattern("[d-M-uuuu h:mm a]")
                        .appendPattern("[d M uuuu h:mm a]")
                        .appendPattern("[d/MMM/uuuu h:mm a]")
                        .appendPattern("[d-MMM-uuuu h:mm a]")
                        .appendPattern("[d MMM uuuu h:mm a]")
                        .appendPattern("[d/MMMM/uuuu h:mm a]")
                        .appendPattern("[d-MMMM-uuuu h:mm a]")
                        .appendPattern("[d MMMM uuuu h:mm a]")
                        .appendPattern("[d/M h:mm a]")
                        .appendPattern("[d-M h:mm a]")
                        .appendPattern("[d M h:mm a]")
                        .appendPattern("[d/MMM h:mm a]")
                        .appendPattern("[d-MMM h:mm a]")
                        .appendPattern("[d MMM h:mm a]")
                        .appendPattern("[d/MMMM h:mm a]")
                        .appendPattern("[d-MMMM h:mm a]")
                        .appendPattern("[d MMMM h:mm a]")

                        .appendPattern("[d/M/uuuu h a]")
                        .appendPattern("[d-M-uuuu h a]")
                        .appendPattern("[d M uuuu h a]")
                        .appendPattern("[d/MMM/uuuu h a]")
                        .appendPattern("[d-MMM-uuuu h a]")
                        .appendPattern("[d MMM uuuu h a]")
                        .appendPattern("[d/MMMM/uuuu h a]")
                        .appendPattern("[d-MMMM-uuuu h a]")
                        .appendPattern("[d MMMM uuuu h a]")
                        .appendPattern("[d/M h a]")
                        .appendPattern("[d-M h a]")
                        .appendPattern("[d M h a]")
                        .appendPattern("[d/MMM h a]")
                        .appendPattern("[d-MMM h a]")
                        .appendPattern("[d MMM h a]")
                        .appendPattern("[d/MMMM h a]")
                        .appendPattern("[d-MMMM h a]")
                        .appendPattern("[d MMMM h a]")

                        .appendPattern("[d/M/uuuu]")
                        .appendPattern("[d-M-uuuu]")
                        .appendPattern("[d M uuuu]")
                        .appendPattern("[d/MMM/uuuu]")
                        .appendPattern("[d-MMM-uuuu]")
                        .appendPattern("[d MMM uuuu]")
                        .appendPattern("[d/MMMM/uuuu]")
                        .appendPattern("[d-MMMM-uuuu]")
                        .appendPattern("[d MMMM uuuu]")
                        .appendPattern("[d/M]")
                        .appendPattern("[d-M]")
                        .appendPattern("[d M]")
                        .appendPattern("[d/MMM]")
                        .appendPattern("[d-MMM]")
                        .appendPattern("[d MMM]")
                        .appendPattern("[d/MMMM]")
                        .appendPattern("[d-MMMM]")
                        .appendPattern("[d MMMM]")

                        .appendPattern("[HHmm]")
                        .appendPattern("[HH:mm]")
                        .appendPattern("[h:mm a]")
                        .appendPattern("[h a]")
                        .parseDefaulting(ChronoField.YEAR, Year.now().getValue());
        DateTimeFormatter dateTimeFormatter = dateTimeFormatterBuilder.toFormatter();
        try {
            return getDate(str.trim(), dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new ChaterpillarException(
                    "Invalid date format! I accept quite a number of common date format, " +
                    "but here is one you can use: DD/MM/YYY HH:MM");
        }
    }

    /**
     * Gets the date and/or time from String with default values
     * FROM:
     * @param s <code>String</code> of Date and/or Time
     * @param format <code>DateTimeFormatter</code> object
     * @return <code>LocalDateTime</code> object
     */
    private LocalDateTime getDate(String s, DateTimeFormatter format) throws DateTimeParseException {
        TemporalAccessor dt = format.parseBest(
                s, LocalDateTime::from, LocalDate::from, LocalTime::from, YearMonth::from);

        if (dt instanceof LocalDate) {
            dateOnly = true;
            return ((LocalDate) dt).atStartOfDay();
        } else if (dt instanceof LocalTime) {
            return ((LocalTime) dt).atDate(LocalDate.now());
        } else if (dt instanceof YearMonth) {
            dateOnly = true;
            return ((YearMonth) dt).atDay(1).atStartOfDay();
        } else {
            return LocalDateTime.from(dt);
        }

    }
    public boolean isSameDay(DateTime dt) {
        return this.dateTime.toLocalDate().isEqual(dt.dateTime.toLocalDate());
    }
    public boolean isWithinDate(DateTime dtStart, DateTime dtEnd) {
        if (dateTime.toLocalDate().isAfter(dtStart.dateTime.toLocalDate())
            && dateTime.toLocalDate().isBefore(dtEnd.dateTime.toLocalDate())) {
            return true;
        } else {
            return dateTime.toLocalDate().isEqual(dtStart.dateTime.toLocalDate())
                    || dateTime.toLocalDate().isEqual(dtEnd.dateTime.toLocalDate());
        }
    }
    @Override
    public String toString() {
        if (dateOnly) {
            return this.dateTime.format(DateTimeFormatter.ofPattern("d/MMM/yyyy"));
        } else {
            return this.dateTime.format(DateTimeFormatter.ofPattern("d/MMM/yyyy hh:mm a"));
        }
    }
}
