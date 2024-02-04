package duke.task;

import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to, boolean isDone){
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }

    @Override
    public String getDataString() {
        return "E | " + (isDone? "1" : "0") + " | " + super.getDescription() + " | " + from + " | " + to;
    }
}
