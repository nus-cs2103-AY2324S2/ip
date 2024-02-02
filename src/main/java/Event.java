public class Event extends Task {
    private String start;
    private String end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public Event(String name, String start, String end, boolean isDone) {
        super(name, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to:" + this.end + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + this.start + " | " + this.end;
    }
}
