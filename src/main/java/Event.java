public class Event extends Task {
    protected String start;
    protected String end;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + this.description + " (from: " + this.start + " to: " +
                this.end + ")";
    }
}
