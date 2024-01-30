public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean done, String from, String to) {
        super(description, done);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getStorageString() {
        return String.format("E | %s | %s | %s", super.getStorageString(), this.from, this.to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
