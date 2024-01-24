public class Event extends Task {
    private String from;
    private String to;

    Event(String text, TaskStatus status, String from, String to) {
        super(text, status);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + "to: " + to;
    }
}
