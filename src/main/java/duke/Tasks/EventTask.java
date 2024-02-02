package duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.time.LocalDate;


/**
 * Represents a task with an event duration.
 */
public class EventTask extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    int CHECK = 0;


    /**
     * Constructs an EventTask object with the given description, start time string, and end time string.
     *
     * @param description     The description of the event task.
     * @param startTimeString The start time string in various formats.
     * @param endTimeString   The end time string in various formats.
     */
    public EventTask(String description, String startTimeString, String endTimeString) {
        super(description);
        this.startTime = parseDateTime(startTimeString);
        this.endTime = parseDateTime(endTimeString);
    }


    /**
     * Parses the time string into a LocalDateTime object.
     *
     * @param time The time string in various formats.
     * @return The LocalDateTime object representing the time.
     * @throws DateTimeParseException If the time string cannot be parsed.
     */
    private LocalDateTime parseDateTime(String time) throws DateTimeParseException {
        LocalDateTime dateTime = null;
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("d/M/yyyy");

        try {
            CHECK = 1;
            dateTime = LocalDateTime.parse(time, formatter1);

        } catch (DateTimeParseException e1) {
            try {
                CHECK = 2;
                dateTime = LocalDateTime.parse(time, formatter2);

            } catch (DateTimeParseException e2) {
                try {
                    CHECK = 3;
                    LocalDate date = LocalDate.parse(time, formatter3);
                    dateTime = date.atStartOfDay();

                } catch (DateTimeParseException e3) {
                    try {
                        CHECK = 4;
                        LocalDate date = LocalDate.parse(time, formatter4);
                        dateTime = date.atStartOfDay();

                    } catch (DateTimeParseException e4) {
                        CHECK = 5;
                        throw new DateTimeParseException("Unable to parse date/time: " + time, time, 0, e2);
                    }

                }

            }
        }

        return dateTime;
    }


    /**
     * Formats the date and time into a string.
     *
     * @param dateTime The LocalDateTime object representing the date and time.
     * @return The formatted string representing the date and time.
     */
    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter;
        if (CHECK == 1 || CHECK == 2) {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);
        } else {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        }

        return dateTime.format(formatter);
    }

    /**
     * Gets the formatted start time string.
     *
     * @return The formatted start time string.
     */
    public String getStartTime() {
        String time = formatDateTime(startTime);
        return time;
    }

    /**
     * Gets the formatted end time string.
     *
     * @return The formatted end time string.
     */
    public String getEndTime() {
        String time = formatDateTime(endTime);
        return time;
    }


    /**
     * Gets the formatted event duration string.
     *
     * @return The formatted event duration string.
     */
    public String getDateTime() {
        return getStartTime() + "-" + getEndTime();
    }

    @Override
    public String tag() {
        return "[E]";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDateTime(startTime) + " to: " + formatDateTime(endTime) + ")";
    }
}

