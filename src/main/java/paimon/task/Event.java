package paimon.task;

public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String createSaveData() {
        return String.format("E | %d | %s | %s | %s\n", (super.isDone() ? 1 : 0), this.description, this.from, this.to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
