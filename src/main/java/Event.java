public class Event extends Task {
    private String from;
    private String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)\n", this.done ? "X" : " ", this.name, this.from, this.to);
    }
}
