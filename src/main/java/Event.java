public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String textFormattedOutput() {
        int intIsDone = isDone ? 1 : 0;
        return String.format("E | %d | %s | %s | %s", intIsDone, description, from, to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}