public class Event extends Task {
    private String from;
    private String to;
    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    public Event(boolean isDone, String desc, String from, String to) {
        super(isDone, desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.description
                + " (from: " + this.from + " to: " + this.to + ")";
    }
}
