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
        String info = String.format("(from: %s to: %s)", start, end);
        return String.format("[T] %s %s %s", this.getStatusIcon(), super.toString(), info);
    }
}
