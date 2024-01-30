public class Event extends Task {
    protected String start;
    protected String end;
    public Event(String description, int isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + this.description + " (from: " + this.start + " to: " +
                this.end + ")";
    }
}
