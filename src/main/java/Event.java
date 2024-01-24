public class Event extends Task {
    private String start;
    private String end;

    public Event(String taskName, String start, String end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                String.format(" (from: %s to: %s)", start, end);
    }
}
