package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Event} class represents event tasks.
 * 
 * <p> This is an immutable class.
 * 
 * <p> Contains:
 * <ul>
 * <li>a {@code String} name.</li>
 * <li>a {@code boolean} done state.</li>
 * <li>a {@code LocalDateTime} start date.</li>
 * <li>a {@code LocalDateTime} end date.</li>
 * </ul>
 */
public class Event implements Task {
    private static DateTimeFormatter dtfOutput = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private final String name;
    private final boolean isDone;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    /**
     * Creates a new {@code Event} object, with {@code done} set to {@code false} by default.
     * 
     * @param name Name of the event task.
     * @param startDate {@code LocalDateTime} object representing the start of the event.
     * @param endDate {@code LocalDateTime} object representing the end of the event.
     */
    public Event(String name, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        isDone = false;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private Event(String name, boolean done, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.isDone = done;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static void setDateTimeFormat(DateTimeFormatter dtf) {
        dtfOutput = dtf;
    }

    /**
     * {@inheritDoc}
     * 
     * @return {@code Event} object with {@code done} set as {@code true}.
     */
    public Event mark() {
        return new Event(name, true, startDate, endDate);
    }

    /**
     * {@inheritDoc}
     * 
     * @return {@code Event} object with {@code done} set as {@code false}.
     */
    public Event unmark() {
        return new Event(name, false, startDate, endDate);
    }

    public boolean nameContains(String s) {
        return name.contains(s);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String d = isDone ? "X" : " ";
        return String.format("[E][%s] %s (from: %s) (to: %s)", d, name,
                startDate.format(dtfOutput), endDate.format(dtfOutput));
    }
}
