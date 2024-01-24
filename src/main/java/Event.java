public class Event extends Task {
    private String from;
    private String to;

    Event(String text, TaskStatus status) {
        super(text, status);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString();
    }
}
