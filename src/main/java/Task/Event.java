package Task;

public class Event extends Task {
    private String from;
    private String to;
    private DateTime start;
    private DateTime end;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.start = new DateTime(from);
        this.end = new DateTime(to);
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.start = new DateTime(from);
        this.end = new DateTime(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start.formatDate() + "hrs to: " + this.end.formatDate() + "hrs)";
    }

    @Override
    public String toFileString() {
        return "E" + super.toFileString() + "|" + this.from + "|" + this.to;
    }
}
