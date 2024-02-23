package dune.task;

import dune.DateTimePrinter;
import dune.Dune;
import dune.DuneException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents an event. An event has a description, a boolean isDone status,
 * and a starting and ending time.
 */
public class Event extends Task {

    /** Starting time of event */
    private LocalDateTime start;

    /** Ending time of event */
    private LocalDateTime end;

    private static final DateTimePrinter DATE_TIME_PRINTER = new DateTimePrinter();

    private static final String BEFORE = "Start date must be before end date";

    // test this method

    /**
     * Constructor for Event.
     *
     * @param description
     * @param start Starting time of event.
     * @param end Ending time of event.
     */
    public Event(String description, String start, String end) throws DuneException {
        super(description);
        this.start = LocalDateTime.parse(start);
        this.end = LocalDateTime.parse(end);
        if (!this.start.isBefore(this.end)) {
            throw new DuneException(BEFORE);
        }
    }


    /**
     * Constructor for Event with isDone.
     *
     * @param description
     * @param start Starting time of event.
     * @param end Ending time of event.
     * @param isDone
     */
    public Event(String description, String start, String end, boolean isDone) throws DuneException {
        super(description, isDone);
        this.start = LocalDateTime.parse(start);
        this.end = LocalDateTime.parse(end);
        if (this.start.isAfter(this.end)) {
            throw new DuneException(BEFORE);
        }
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event e = (Event) o;
            boolean x = e.getDescription().equals(this.getDescription());
            boolean y = e.getStart().equals(this.getStart());
            boolean z = e.getEnd().equals(this.getEnd());
            return x && y && z;
        } else {
            return false;
        }
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DATE_TIME_PRINTER.print(this.start)
                + " to: " + DATE_TIME_PRINTER.print(this.end) + ")";
    }

}
