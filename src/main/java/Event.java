public class Event extends Task {
    private String from;
    private String to;

    public Event(String n, String from, String to) {
        super(n);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}
