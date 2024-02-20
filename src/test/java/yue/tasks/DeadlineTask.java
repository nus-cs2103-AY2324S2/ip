package yue.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    private final LocalDateTime deadline;
    int check = 0;

    public DeadlineTask(String description, String deadlineStr) {
        super(description);
        this.deadline = parseDateTime(deadlineStr);
    }

    private LocalDateTime parseDateTime(String time) throws DateTimeParseException {
        LocalDateTime dateTime = null;
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("d/M/yyyy");

        try {
            check = 1;
            dateTime = LocalDateTime.parse(time, formatter1);

        } catch (DateTimeParseException e1) {
            try {
                check = 2;
                dateTime = LocalDateTime.parse(time, formatter2);

            } catch (DateTimeParseException e2) {
                try {
                    check = 3;
                    LocalDate date = LocalDate.parse(time, formatter3);
                    dateTime = date.atStartOfDay();

                } catch (DateTimeParseException e3) {
                    try {
                        check = 4;
                        LocalDate date = LocalDate.parse(time, formatter4);
                        dateTime = date.atStartOfDay();

                    } catch (DateTimeParseException e4) {
                        throw new DateTimeParseException("Unable to parse date/time: " + time, time, 0, e2);
                    }

                }

            }
        }

        return dateTime;
    }

    String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter;
        if (check == 1 || check == 2) {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);
        } else {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        }

        return dateTime.format(formatter);
    }

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
