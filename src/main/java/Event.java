public class Event extends Task {
    protected String start;
    protected String end;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String status, String description, String start, String end) {
        super(status.equals("1"), description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toFile() {
        return "E | " + (isDone ? "1" : "0") + " | " + description
                + " | " + start + " - " + end;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
