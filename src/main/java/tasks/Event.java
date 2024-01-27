package tasks;

import java.time.LocalDate;

import utils.DateTime;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        this(description, false, from, to);
    }

    public Event(String description, boolean isDone, LocalDate from, LocalDate to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFile() {
        return "E | " + super.toFile() + " | " + DateTime.dateToFile(this.from) + " | " + DateTime.dateToFile(this.to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTime.dateToString(this.from) + " to: " + DateTime.dateToString(this.to) + ")";
    }
}
