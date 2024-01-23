package task;
public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}
