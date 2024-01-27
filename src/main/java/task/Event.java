package task;

import util.CSVUtil;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(boolean isMarked, String description, String from, String to) {
        super(isMarked, description);
        this.from = from;
        this.to = to;
    }

    @Override
    public CSVUtil format() {
        return new CSVUtil("E", String.valueOf(super.isMarked), super.description, from, to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}
