package duke.task;

import duke.utils.DateTime;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task {
    protected DateTime from;
    protected DateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = new DateTime(from);
        this.to = new DateTime(to);
    }

    @Override
    public String writeContent() {
        return "E |" + (this.isDone ? " 1 | " : " 0 | ") + this.getDescription()
                + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}