package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public Event(String Task, String taskType, String start, String end) {
        super(Task, taskType);
        this.start = LocalDateTime.parse(start,formatter);
        this.end = LocalDateTime.parse(end,formatter);
    }
    public String toString() {
        return this.getTaskType() + this.getStatus() + " " + this.getTask() + this.getPeriod();
    }

    public String getPeriod() {
        return " (From: " + startDateToString() + " To: " + endDateToString() + ")";
    }

    public String announcement() {
        return "New Event created!";
    }

    public String saveString() {
        return this.getTaskTypeSingle() + "|" + this.getStatusBinary() + "|" + this.getTask() + "|"
                + this.startDateToString() + "|" + this.endDateToString();
    }
    public String startDateToString() {
        return this.start.format(formatter).replace("T", " ");
    }

    public String endDateToString() {
        return this.end.format(formatter).replace("T", " ");
    }
}
