package data;

import data.exception.CoDriverException;

import java.time.LocalDate;

public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from
                + " to: " + this.to + ")";
    }

    @Override
    public String toSaveString() {
        return "E|" + super.toSaveString() + "|" + this.from + "~" + this.to;
    }
}