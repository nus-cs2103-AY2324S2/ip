package simpli.tasks;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(boolean isDone, String name, String from, String to) {
        super(isDone, name);
        this.from = from;
        this.to = to;
    }

    public String toCsv() {
        return String.format("Event,%s,%s,%s", super.toCsv(), this.from, this.to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}
