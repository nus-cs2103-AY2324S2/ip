package task;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return this.from;
    }
    
    public String getTo() {
        return this.to;
    }
    
    @Override
    public String toStorageString() {
        return "E | " + super.toStorageString() + String.format(" | %s | %s", this.getFrom(), this.getTo());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", this.getFrom(), this.getTo());
    }
}
