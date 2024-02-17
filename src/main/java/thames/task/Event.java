package thames.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for tasks with a duration.
 */
public class Event extends Task{
    protected LocalDate from;
    protected LocalDate to;

    public Event(String name, String from, String to) {
        super(name);
        this.to = LocalDate.parse(to);
        this.from = LocalDate.parse(from);
    }

    public LocalDate getFrom() {
        return this.from;
    }

    public LocalDate getTo() {
        return this.to;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            return super.equals(o) &&
                    ((Event) o).from.equals(this.from) && ((Event) o).to.equals(this.to);
        }
        return false;
    }
}
