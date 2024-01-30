public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start.trim();
        this.end = end.trim();
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }

    @Override
    public String getDetails() {
        return "event | " + (this.isDone ? "1 " : "0 | ") + this.description
                + " | " + this.start + " | " + this.end;
    }
}
