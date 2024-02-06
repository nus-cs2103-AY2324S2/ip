package tasks;

public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, String status) {
        super(description, status);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toSaveFormat() {
        return "E " + super.toSaveFormat() + " /from " + from + " /to " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
