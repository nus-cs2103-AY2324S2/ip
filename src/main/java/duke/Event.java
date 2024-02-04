package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    LocalDate start,deadline;
    public Event(String description, LocalDate start, LocalDate deadline, boolean done) {
        super(description, done);
        this.start = start;
        this.deadline = deadline;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%s (from: %s; to: %s)",
                super.toString(),
                start.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
            );
    }
}
