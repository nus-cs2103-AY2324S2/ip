public class Event extends Task {
    private String start;
    private String end;
    private TaskType type;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        this.type = TaskType.E;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)", type, super.toString(), start, end);
    }
}
