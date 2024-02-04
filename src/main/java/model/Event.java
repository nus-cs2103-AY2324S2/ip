package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event implements Task {
    private static DateTimeFormatter dtfOutput = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private final String name;
    private final boolean done;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    Event(String name, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.done = false;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private Event(String name, boolean done, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.done = done;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static void setDateTimeFormat(DateTimeFormatter dtf) {
        dtfOutput = dtf;
    }

    public Event mark() {
        return new Event(this.name, true, this.startDate, this.endDate);
    }

    public Event unmark() {
        return new Event(this.name, false, this.startDate, this.endDate);
    }

    @Override
    public String toString() {
        String d = this.done ? "X" : " ";
        return String.format("[E][%s] %s (from: %s) (to: %s)", d, name,
                startDate.format(dtfOutput), endDate.format(dtfOutput));
    }
}
