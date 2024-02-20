package yue.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.time.format.DateTimeParseException;


/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    private final LocalDateTime deadline;
    private int dateFormatCheck = 0;

    /**
     * Constructs a DeadlineTask object with the given description and deadline string.
     *
     * @param description The description of the task.
     * @param deadlineStr The deadline string in various formats.
     */
    public DeadlineTask(String description, String deadlineStr) {
        super(description);
        assert deadlineStr != null : "Deadline string cannot be null";
        this.deadline = parseDateTime(deadlineStr);
        assert deadline != null : "Deadline LocalDateTime object cannot be null";
    }


    /**
     * Parses the deadline string into a LocalDateTime object.
     *
     * @param time The deadline string in various formats.
     * @return The LocalDateTime object representing the deadline.
     * @throws DateTimeParseException If the deadline string cannot be parsed.
     */
    private LocalDateTime parseDateTime(String time) throws DateTimeParseException {
        assert time != null : "Deadline string cannot be null";
        LocalDateTime dateTime = null;
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("d/M/yyyy");

        try {
            dateFormatCheck = 1;
            dateTime = LocalDateTime.parse(time, formatter1);

        } catch (DateTimeParseException e1) {
            try {
                dateFormatCheck = 2;
                dateTime = LocalDateTime.parse(time, formatter2);

            } catch (DateTimeParseException e2) {
                try {
                    dateFormatCheck = 3;
                    LocalDate date = LocalDate.parse(time, formatter3);
                    dateTime = date.atStartOfDay();

                } catch (DateTimeParseException e3) {
                    try {
                        dateFormatCheck = 4;
                        LocalDate date = LocalDate.parse(time, formatter4);
                        dateTime = date.atStartOfDay();

                    } catch (DateTimeParseException e4) {
                        throw new DateTimeParseException("Unable to parse date/time as the time might be missing or in a wrong format "
                                + time, time, 0, e2);
                    }

                }

            }
        }
        assert dateTime != null : "Parsed LocalDateTime object cannot be null";
        return dateTime;
    }


    /**
     * Formats the deadline date and time into a string.
     *
     * @param dateTime The LocalDateTime object representing the deadline.
     * @return The formatted string representing the deadline.
     */
    String formatDateTime(LocalDateTime dateTime) {
        assert dateTime != null : "LocalDateTime object cannot be null";
        DateTimeFormatter formatter;
        if (dateFormatCheck == 1 || dateFormatCheck == 2) {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);
        } else {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        }

        return dateTime.format(formatter);
    }



    /**
     * Gets the formatted deadline date and time string.
     *
     * @return The formatted deadline date and time string.
     */
    public String getDateTime() {
        return formatDateTime(deadline);
    }



    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDateTime(deadline) + ")";
    }

    @Override
    public String tag() {
        return "[D]";
    }

}
