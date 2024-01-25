public class Event extends Task {
    private String from;
    private String to;

    Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
