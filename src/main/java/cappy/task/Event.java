package cappy.task;

import cappy.parser.Parser;

import java.time.LocalDateTime;

public class Event extends Task {
    public static final String TYPE_SYMBOL = "E";
    private final LocalDateTime begin;
    private final LocalDateTime end;

    public Event(String description, LocalDateTime begin, LocalDateTime end) {
        super(description);
        this.begin = begin;
        this.end = end;
    }

    public Event(String description, boolean isDone, LocalDateTime begin, LocalDateTime end) {
        super(description, isDone);
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toCsv() {
        return TYPE_SYMBOL
                + ","
                + (super.getDone() ? "1" : "0")
                + ","
                + super.getDescription()
                + ","
                + Parser.dateTimeToString(begin)
                + ","
                + Parser.dateTimeToString(end);
    }

    @Override
    public String toString() {
        return "["
                + TYPE_SYMBOL
                + "]"
                + super.toString()
                + " (from: "
                + Parser.dateTimeToString(begin)
                + " to: "
                + Parser.dateTimeToString(end)
                + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Event)) {
            return false;
        }
        Event other = (Event) obj;
        return super.equals(other) && begin.equals(other.begin) && end.equals(other.end);
    }
}
