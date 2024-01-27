package Task;

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

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.from + " to:" + this.to + ")";
    }

    @Override
    public String toFileString() {
        return "E" + super.toFileString() + "|" + this.from + "|" + this.to;
    }
}
