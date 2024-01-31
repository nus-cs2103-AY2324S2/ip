public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to, boolean isComplete) { // Adjusted constructor
        super(description);
        this.from = from;
        this.to = to;
        this.isComplete = isComplete;
    }

    @Override
    public String toFileFormat() {
        return String.format("E | %d | %s | %s to %s", isComplete ? 1 : 0, description, from, to);
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (from: " + from + " to: " + to + ")";
    }

}
