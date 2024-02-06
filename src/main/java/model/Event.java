package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event implements Task{
    private final String name;
    private final boolean done;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    public static DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    Event(String name, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        done = false;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private Event(String name, boolean done, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.done = done;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event mark() {
        return new Event(name, true, startDate, endDate);
    }

    public Event unmark() {
        return new Event(name, false, startDate, endDate);
    }

    public boolean nameContains(String s) {
        return name.contains(s);
    }

    @Override
    public String toString() {
        String d = done ? "X" : " ";
        return String.format("[E][%s] %s (from: %s) (to: %s)", d, name,
                startDate.format(dtf), endDate.format(dtf));
    }
}
