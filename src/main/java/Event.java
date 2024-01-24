public class Event extends Task {
    protected String fromWhen;
    protected String toWhen;

    public Event(String name, String fromWhen, String toWhen) {
        super(name);
        this.fromWhen = fromWhen;
        this.toWhen = toWhen;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromWhen + " to: " + toWhen + ")";
    }
}
