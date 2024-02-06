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
    private final boolean done;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    /**
     * Contructor to create a new {@code Event} object, with {@code done} set to {@code false} by default.
     * 
     * @param name Name of the event task.
     * @param startDate {@code LocalDateTime} object representing the start of the event.
     * @param endDate {@code LocalDateTime} object representing the end of the event.
     */
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

    /**
     * {@inheritDoc}
     * 
     * @return {@code Event} object with {@code done} set as {@code true}.
     */
    public Event mark() {
        return new Event(this.name, true, this.startDate, this.endDate);
    }

    /**
     * {@inheritDoc}
     * 
     * @return {@code Event} object with {@code done} set as {@code false}.
     */
    public Event unmark() {
        return new Event(this.name, false, this.startDate, this.endDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String d = this.done ? "X" : " ";
        return String.format("[E][%s] %s (from: %s) (to: %s)", d, name,
                startDate.format(dtfOutput), endDate.format(dtfOutput));
    }
}
