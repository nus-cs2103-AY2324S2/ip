package lex.tasks;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Event(@JsonProperty("title") String title, @JsonProperty("start") LocalDate start,
                 @JsonProperty("end") LocalDate end) {
        super(title);
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), start.format(DateTimeFormatter.ofPattern(
                "MMM dd yyyy")), end.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
