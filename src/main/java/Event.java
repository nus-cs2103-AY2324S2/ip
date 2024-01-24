public class Event extends Task {
    String from;
    String to;
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("%s %s (from: %s to: %s)", "[E]", super.toString(), this.from, this.to);
    }
}
