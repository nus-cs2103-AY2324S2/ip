package lex.tasks;

import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Event(String title, LocalDate start, LocalDate end) {
        super(title);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), start, end);
    }
}
