public class Event extends Task{
    private String start;
    private String end;

    public Event(boolean isDone, String description, String start, String end) {
        super(isDone, description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String toSave() {
        return "[E]|" + super.toSave() + "|" + start + "|" + end;
    }
}
