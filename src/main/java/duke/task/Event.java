package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * The class representing an Event task.
 * */
public class Event extends Task {
    String type = "[E]";
    LocalDate start;
    LocalDate end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDate.parse(start.replace(" ", ""));
        this.end = LocalDate.parse(end.replace(" ", ""));
    }

    @Override
    public String toString() {
        return this.type + this.display + " " + this.description + "(from: " + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "to: " + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toDBString() {
        String display;
        if (Objects.equals(this.display, "[ ]")) {
            display = "0";
        } else {
            display = "1";
        }
        return "E|" + display + "|" + this.description + "|" + this.start + "|" + this.end;
    }
}
