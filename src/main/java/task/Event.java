package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event is a subclass of task.Task. It stores information of a task including
 * the start and end date.
 * @author Koo Zhuo Hui
 */
public class Event extends Task {
    private String from;
    private String to;
    private LocalDateTime end;

    /**
     * Constructor for Event with a task name, start and end date.
     * @param s Name of the event.
     * @param from The start timing of the event.
     * @param to The end of the event.
     */
    public Event(String s, String from, String to) {
        super(s);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime start = LocalDateTime.parse(from, formatter);
            end = LocalDateTime.parse(to, formatter);
            this.from = start.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
            this.to = end.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        } catch (DateTimeParseException e) {
            this.from = from;
            this.to = to;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode() {
        String s = "E|" + (super.getStatus() ? 1 : 0) + "|" + super.getTask()
                + "|" + from + "|" + to;
        return s;
    }

    @Override
    public String toString() {
        return "[E][" + (super.getStatus() ? "X" : " ") + "] " + super.getTask()
                + "(From: " + from + " To: " + to + ")";

    }
}
