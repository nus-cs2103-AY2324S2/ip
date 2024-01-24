public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }

    @Override
    public String fileString() {
        return "E " + super.fileString() + " | " + this.start + " to " + this.end;
    }

}
