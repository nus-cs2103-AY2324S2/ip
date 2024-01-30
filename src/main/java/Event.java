public class Event extends Task {
    private String from;
    private String to;

    public Event(boolean done, String name, String from, String to) {
        super(done, name);
        this.from = from;
        this.to = to;
    }
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toSavedString() {
        return String.format("E,%s,%s,%s,%s", this.done ? '1' : '0', this.name, this.from, this.to);
    }

    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)\n", this.done ? "X" : " ", this.name, this.from, this.to);
    }
}
