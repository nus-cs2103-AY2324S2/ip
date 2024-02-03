package dune.task;

import dune.DateTimePrinter;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private LocalDateTime start;
    private LocalDateTime end;

    private static final DateTimePrinter DATE_TIME_PRINTER = new DateTimePrinter();

    private static final String BEFORE = "Start date must be before end date";

    // test this method
    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDateTime.parse(start);
        this.end = LocalDateTime.parse(end);
        if (!this.start.isBefore(this.end)) {
            throw new DateTimeParseException(BEFORE, "", 0);
        }
    }


    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        this.start = LocalDateTime.parse(start);
        this.end = LocalDateTime.parse(end);
        if (this.start.isAfter(this.end)) {
            throw new DateTimeParseException(BEFORE, "", 0);
        }
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DATE_TIME_PRINTER.print(this.start)
                + " to: " + DATE_TIME_PRINTER.print(this.end) + ")";
    }

}
