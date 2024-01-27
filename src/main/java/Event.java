public class Event extends Task {
    protected String from;
    protected String to;

    private static final String type = "E";

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toSaveString() {
        return type + "," + super.toSaveString() + "," + from + "," + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }
}
