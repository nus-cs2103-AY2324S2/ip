public class Event extends Task {

    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String toSavableFormat() {
        return this.uuid + "|E|" + this.description + "|" + this.done + "|" + start + "|" + end;
    }

    @Override
    public String getType() {
        return "[E]";
    }
}
