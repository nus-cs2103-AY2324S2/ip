package duke.Task;

import duke.Task;

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

    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter;
        if (check == 1 || check == 2) {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);
        } else {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        }

        return dateTime.format(formatter);
    }

    public LocalDateTime getDateTime() {
        return deadline;
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

/*public class duke.Task.DeadlineTask extends duke.Task {
    private String by;
    int check = 0;

    private LocalDateTime dateTime;

    public duke.Task.DeadlineTask(String task) {
        super(task);
        parseDeadline(task);
        this.dateTime = parseDateTime(by);
    }

    private void parseDeadline(String task) {
        String[] split = task.split("/by", 2);

        if (split.length == 2) {
            this.task = split[0];
            this.by = split[1].trim();
        }
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

    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter;
        if (check == 1 || check == 2) {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);
        } else {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        }

        return dateTime.format(formatter);
    }


    public String getDateTime() {
        String time = formatDateTime(dateTime);
        return time;
    }


    public String getBy() {
        return by;
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



/*public class duke.Task.DeadlineTask extends duke.Task {
    private String by;

    private LocalDateTime byDateTime;

    public duke.Task.DeadlineTask(String task) {
        super(task);
        parseDeadline(task);
    }

    private void parseDeadline(String task) {
        String[] split = task.split("/by", 2);

        if (split.length == 2) {
            this.task = split[0];
            this.by = split[1].trim();
        }
    }

    public String getBy() {
        return by;
    }
    @Override
    public String toString() {
        return tag() + mark() + " " + task + " (by: " + by + ")";
    }

    @Override
    public String tag() {
        return "[D]";
    }

}*/