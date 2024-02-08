package main.java.emis.task;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(boolean isDone, String description, String from, String to) {
        super(isDone, description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String storeString() {
        return "E | " + super.storeString() + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        return"[E]" + super.toString() + "(from: " + this.from + " to: " + this.to + ")";
    }
}