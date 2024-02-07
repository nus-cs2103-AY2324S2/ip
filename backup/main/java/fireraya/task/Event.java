package fireraya.task;

public class Event extends Task{

    protected String from;
    protected String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String saveFormat() {
        return String.format("E | %d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
