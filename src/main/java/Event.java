public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String event, String start, String end) {
        super(event);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.start + " to:" + this.end + ")";
    }

}