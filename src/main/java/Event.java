public class Event extends Task {
    private String start;
    private String end;

    Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public Event(String name, String start, String end, boolean isDone) {
        this(name, start, end);
        this.isDone = isDone;
    }

    @Override
    public String fileRepresentation() {
        return String.format("E | %s | %s | from: %s to: %s",
                this.getStatusIcon(),
                this.getDescription(),
                this.start,
                this.end);
    }
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.start, this.end);
    }
}
