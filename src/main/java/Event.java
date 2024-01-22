public class Event extends Task{
    private String start;
    private String by;
    public Event(Boolean status, String detail, String start, String by) {
        super(status, detail);
        this.start = start;
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: "  + start + " to: " + by + ")";
    }
}
