public class Event extends Task{
    private String from;
    private String by;
    public Event(String description, String from, String by) {
        super(description);
        this.from = from;
        this.by = by;
    }

    public Event(String description, String from, String by, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.by = by;
    }
    @Override
    public String show() {
        super.status = isDone? "X": " ";
        String fromByFormat = "(from: " + from + " to: " + by + ")";
        return "[E]" + "[" + status + "]" + " " + this.description + " " + fromByFormat;
    }
}
