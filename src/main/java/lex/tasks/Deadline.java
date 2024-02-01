package lex.tasks;

import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate end;

    public Deadline(String title, LocalDate end) {
        super(title);
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), end);
    }
}
