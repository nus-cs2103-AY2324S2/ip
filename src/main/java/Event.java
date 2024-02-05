public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public Event(String name, String start, String end, boolean isDone) {
        super(name);
        this.start = start;
        this.end = end;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String str = super.toString();
        return "[E]" + str + " (from: " + this.start + " to: " + this.end + ")";
    }
}
