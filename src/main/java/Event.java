public class Event extends Task {
    private final String symbol = "[E]";
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super("[E]", description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return this.symbol + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
