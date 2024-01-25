public class Event extends Task {
    String start;
    String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + (this.isDone ? "[X] " : "[ ] ") + this.description + " (from: " + this.start + " to: " + this.end + ")";
    }
}
