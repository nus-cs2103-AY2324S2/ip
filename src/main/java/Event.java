public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String eventFrom, String eventTo) {
        super(description);
        this.from = eventFrom;
        this.to = eventTo;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toFileFormat() {
        return super.toFileFormat() + String.format(" | %s-%s", this.from, this.to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
