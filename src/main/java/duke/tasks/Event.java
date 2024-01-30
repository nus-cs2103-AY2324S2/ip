package duke.tasks;

public class Event extends Task {
    private String start;
    private String by;
    public Event(Boolean status, String detail, String start, String by) {
        super(status, detail);
        this.start = start;
        this.by = by;
    }

    @Override
    public String inFileStringFormat() {
        return "E|" + super.inFileStringFormat() + "|" + this.start + "|" + this.by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: "  + start + " to: " + by + ")";
    }
}
