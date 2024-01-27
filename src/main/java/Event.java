public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String n, String from, String to) {
        super(n);
        this.from = from;
        this.to = to;
    }

    public Event(String n, boolean d, String from, String to) {
        super(n, d);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}
