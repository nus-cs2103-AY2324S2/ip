package lex.tasks;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate end;

    public Deadline(@JsonProperty("title") String title, @JsonProperty("end") LocalDate end) {
        super(title);
        this.end = end;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), end.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
