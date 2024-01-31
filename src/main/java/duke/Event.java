package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatus() + " " + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E | " + (super.isDone ? "1" : "0") + " | " + super.description + " | " + this.from + " | " + this.to;
    }

    public static Event fromSaveFormat(String[] info) {
        Event loadedTask =  new Event(info[2], LocalDate.parse(info[3]), LocalDate.parse(info[4]));
        if (info[1].equals("1")) {
            loadedTask.markAsDone();
        }
        return loadedTask;
    }
}