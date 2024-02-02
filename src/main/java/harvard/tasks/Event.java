package harvard.tasks;

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

    public Event(String description, LocalDate from, LocalDate to, Boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        if (isDone) super.mark();
    }

    @Override
    public String saveString() {
        return "E," + (super.isDone ? "1," : "0,") + super.getDescription() + "," +
                this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "," +
                this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " +
                this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
