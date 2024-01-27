public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        this(description, false, from, to);
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFile() {
        return "E | " + super.toFile() + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
