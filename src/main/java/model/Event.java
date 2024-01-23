package model;

public class Event extends Task{
    private String from;
    private String to;

    public Event(String label, String from, String to) {
        super(label);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
