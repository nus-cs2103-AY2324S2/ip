public class Event extends Task{
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + "to: " + end + ")";
    }

    public String getAt() {
        return this.start + " from " + this.end;
    }

    @Override
    public String toFileString() {
        // Format: E | 0/1 | description | at from to
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + getAt();
    }
}
