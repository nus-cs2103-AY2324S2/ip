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
        String status = getStatusIcon();
        return "[E][" + status + "] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " " + to;
    }
}
