package chaterpillar.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

import chaterpillar.exceptions.ChaterpillarException;

/**
 * Custom date and time class for handling dates within this application.
 *
 * @author marclamp
 */

public class DateTime {
    private final LocalDateTime dateTime;
    private boolean hasOnlyDate;
    private boolean hasOnlyTime;

    /**
     * Constructor for this class.
     *
     * @param date date and time in accepted formats.
     * @throws ChaterpillarException if the string provided is in the
     *     invalid/unaccepted format.
     */
    public DateTime(String date) throws ChaterpillarException {
        this.hasOnlyDate = false;
        this.hasOnlyTime = false;
        this.dateTime = parseDateTime(date);
    }

    /**
     * Overloaded Constructor to accept a <code>LocalDateTime</code>
     * object.
     *
     * @param date date and time in <code>LocalDateTime</code> object
     */
    public DateTime(LocalDate date) {
        this.hasOnlyDate = true;
        this.hasOnlyTime = false;
        this.dateTime = date.atTime(0, 0);
    }

    /**
     * Parses the string of date and/or time to a
     * <code>LocalDateTime</code> object.
     *
     * @param str <code>String</code> of date and/or time
     * @return <code>LocalDateTime</code> object
     * @throws ChaterpillarException if the string provided is in the
     *     invalid/unaccepted format.
     */
    public LocalDateTime parseDateTime(String str) throws ChaterpillarException {
        /* Solution adapted from:
            https://www.baeldung.com/java-datetimeformatter
            Specifically, how to use DateTimeFormatterBuilder and appending patterns.
         */
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
                    "Invalid date format! I accept quite a number of common date format, "
                    + "but here is one you can use: DD/MM/YYY HH:MM");
        }
    }

    /**
     * Gets the date and/or time from String with default values.
     *
     * @param s <code>String</code> of Date and/or Time
     * @param format <code>DateTimeFormatter</code> object
     * @return <code>LocalDateTime</code> object
     */
    private LocalDateTime getDate(String s, DateTimeFormatter format)
            throws ChaterpillarException {
        assert !s.isBlank() : "DateTime String should not be blank";
        try {
            TemporalAccessor dt = format.parseBest(s, LocalDateTime::from,
                    LocalDate::from, LocalTime::from, YearMonth::from);

            if (dt instanceof LocalDate) {
                hasOnlyDate = true;
                return ((LocalDate) dt).atStartOfDay();
            } else if (dt instanceof LocalTime) {
                return ((LocalTime) dt).atDate(LocalDate.now());
            } else if (dt instanceof YearMonth) {
                hasOnlyDate = true;
                return ((YearMonth) dt).atDay(1).atStartOfDay();
            } else {
                return LocalDateTime.from(dt);
            }
        } catch (DateTimeParseException e) {
            throw new ChaterpillarException("Error in parsing string for date/time.\n"
                                            + "I accept quite a number of common date format, \n"
                                            + "but here is one you can use: DD/MM/YYY HH:MM");
        }
    }

    /**
     * Checks if the current <code>DateTime</code> object has the
     * same date as the <code>DateTim</code> provided.
     *
     * @param date to check if it has the same date as current
     *     <code>DateTime</code> object.
     * @return <code>true</code>> if the 2 <code>DateTime</code>
     *     objects has the same date.
     */
    public boolean isSameDay(DateTime date) {
        return this.dateTime.toLocalDate().isEqual(date.dateTime.toLocalDate());
    }

    /**
     * Checks if the current <code>DateTime</code> object is within
     * the range of dates provided.
     *
     * @param dtStart start of the period (inclusive)
     * @param dtEnd end of the period (inclusive)
     * @return <code>true</code> if it is within the date
     *     and <code>false</code> if it is not.
     */
    public boolean isWithinDate(DateTime dtStart, DateTime dtEnd) {
        boolean isAfterStartDate = dateTime.toLocalDate().isAfter(dtStart.dateTime.toLocalDate());
        boolean isBeforeEndDate = dateTime.toLocalDate().isBefore(dtEnd.dateTime.toLocalDate());
        boolean isEqualStartDate = dateTime.toLocalDate().isEqual(dtStart.dateTime.toLocalDate());
        boolean isEqualEndDate = dateTime.toLocalDate().isEqual(dtEnd.dateTime.toLocalDate());

        if (isAfterStartDate && isBeforeEndDate) {
            return true;
        } else {
            return isEqualStartDate || isEqualEndDate;
        }
    }
    @Override
    public String toString() {
        if (hasOnlyDate) {
            return this.dateTime.format(DateTimeFormatter.ofPattern("d/MMM/yyyy"));
        } else {
            return this.dateTime.format(DateTimeFormatter.ofPattern("d/MMM/yyyy hh:mm a"));
        }
    }
}
