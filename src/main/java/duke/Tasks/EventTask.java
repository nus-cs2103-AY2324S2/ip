package duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.time.LocalDate;


public class EventTask extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    int check = 0;

    public EventTask(String description, String startTimeString, String endTimeString) {
        super(description);
        this.startTime = parseDateTime(startTimeString);
        this.endTime = parseDateTime(endTimeString);
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
                        check = 5;
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

    public String getStartTime() {
        String time = formatDateTime(startTime);
        return time;
    }

    public String getEndTime() {
        String time = formatDateTime(endTime);
        return time;
    }

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


/*public class duke.Tasks.Task.EventTask extends duke.Tasks.Task {
    private String from;
    private String to;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    int check;

    public duke.Tasks.Task.EventTask(String task) {
        super(task);
        parseEvent(task);
        this.startTime = parseDateTime(from);
        this.endTime = parseDateTime(to);
    }

    private void parseEvent(String task) {
        String[] split = task.split("/from", 2);

        if (split.length == 2) {
            this.task = split[0];

            String[] details = split[1].split("/to", 2);
            if (details.length == 2) {
                this.from = details[0].trim();
                this.to = details[1].trim();
            }
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
                        check = 5;
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


    public String getStartTime() {
        String time = formatDateTime(startTime);
        return time;
    }

    public String getEndTime() {
        String time = formatDateTime(endTime);
        return time;
    }

    public String getDateTime() {
        return getStartTime() + "-" + getEndTime();
    }

    public String getTime() {
        return from + "-" + to;
    }

    @Override
    public String tag() {
        return "[E]";
    }

    @Override
    public String toString() {
        return tag() + mark() + " " + task + " (from: " + getStartTime() + " to: " + getEndTime() + ")";
    }
}*/