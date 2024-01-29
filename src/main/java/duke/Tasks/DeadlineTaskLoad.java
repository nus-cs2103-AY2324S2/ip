package duke.Task;

import duke.Task;

public class DeadlineTaskLoad extends Task {
    private String by;

    public DeadlineTaskLoad(String task, String by) {
        super(task);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String tag() {
        return "[D]";
    }

}



/*import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class duke.Task.DeadlineTaskLoad extends duke.Task {
    private String by;

    int check = 0;

    private LocalDateTime dateTime;


    public duke.Task.DeadlineTaskLoad(String task, String by) {
        super(task);
        this.by = by;
        this.dateTime = parseDateTime(by);
    }

    private LocalDateTime parseDateTime(String time) throws DateTimeParseException {
        LocalDateTime dateTime = null;
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("d/M/yyyy");
        DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("MMM dd yyyy");

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
                        try {
                            check = 4;
                            LocalDate date = LocalDate.parse(time, formatter5);
                            dateTime = date.atStartOfDay();

                        } catch (DateTimeParseException e5) {
                            throw new DateTimeParseException("Unable to parse date/time: " + time, time, 0, e2);
                        }
                    }

                }

            }
        }

        return dateTime;
    }

    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter;
        if (check == 1 || check == 2) {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);
        } else {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        }

        return dateTime.format(formatter);
    }


    public String getBy() {
        return by;
    }

    public String getDateTime() {
        String time = formatDateTime(dateTime);
        return time;
    }

    @Override
    public String toString() {
        return tag() + super.toString() + " (by: " + formatDateTime(dateTime) + ")";
    }

    @Override
    public String tag() {
        return "[D]";
    }

}*/

