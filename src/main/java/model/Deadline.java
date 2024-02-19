package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Deadline} class represents deadline tasks.
 * 
 * <p> This is an immutable class.
 * 
 * <p> Contains:
 * <ul>
 * <li>a {@code String} name.</li>
 * <li>a {@code boolean} done state.</li>
 * <li>a {@code LocalDateTime} deadline.</li>
 * </ul>
 */
public class Deadline implements Task {
    private static DateTimeFormatter dtfOutput = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private final String name;
    private final boolean isDone;
    private final LocalDateTime deadline;

    /**
     * Creates a new {@code Deadline} object, with {@code done} set to {@code false} by default.
     * 
     * @param name Name of the deadline task.
     * @param deadline {@code LocalDateTime} object representing the deadline.
     */
    public Deadline(String name, LocalDateTime deadline) {
        this.name = name;
        isDone = false;
        this.deadline = deadline;
    }

    private Deadline(String name, boolean done, LocalDateTime deadline) {
        this.name = name;
        this.isDone = done;
        this.deadline = deadline;
    }

    public static void setDateTimeFormat(DateTimeFormatter dtf) {
        dtfOutput = dtf;
    }

    /**
     * {@inheritDoc}
     * 
     * @return {@code Deadline} object with {@code done} set as {@code true}.
     */
    public Deadline mark() {
        return new Deadline(name, true, deadline);
    }

    /**
     * {@inheritDoc}
     * 
     * @return {@code Deadline} object with {@code done} set as {@code false}.
     */
    public Deadline unmark() {
        return new Deadline(name, false, deadline);
    }

    public boolean nameContains(String s) {
        return name.contains(s);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String d = this.isDone ? "X" : " ";
        return String.format("[D][%s] %s (by: %s)", d, name, deadline.format(dtfOutput));
    }
}
