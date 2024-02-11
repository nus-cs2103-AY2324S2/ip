package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    public Event(String desc, String start, String end) {
        super(desc);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    public Event(String desc, boolean isDone, String start, String end) {
        super(desc, isDone);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    public String toStore() {
        // need to store status as well
        return "E | " + super.toStore() + " | " + start + " | " + end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
