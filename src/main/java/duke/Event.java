package duke;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String task, String taskType, String start, String end) {
        super(task, taskType);
        this.start = LocalDateTime.parse(start, formatter);
        this.end = LocalDateTime.parse(end, formatter);
    }
    public String toString() {
        return this.getTaskType() + this.getStatus() + " "
                + this.getTask() + this.getPeriod();
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
