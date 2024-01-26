public class Event extends Task {
    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String isDone, String description, String start, String end) {
        super(isDone, description);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        String info = String.format("(from: %s to: %s)", start, end);
        return String.format("[E]%s%s %s", this.getStatusIcon(), super.toString(), info);
    }
}
