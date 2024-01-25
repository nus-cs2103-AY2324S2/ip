public class Event extends Task {
    private static final String id = "[E]";
    private final String from;
    private final String to;

    Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return id + super.toString() + "(from: " + this.from + " to: " + this.to + ")";
    }
}
