package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime date;
    public Deadline(String title, LocalDateTime date) {
        super(title);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mma")) + ", " + date.getDayOfWeek() + ")";
    }
}
