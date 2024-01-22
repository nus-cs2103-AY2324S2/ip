package model;

public class Event extends Task {
    private final String from;
    private final String to;
    public Event(String title, String from, String to) {
        super(title);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + "  to: " + this.to + ")";
    }
}
