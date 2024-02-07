package univus.task;
public class Event extends Task {
    protected String start;
    protected String end;
    public Event(String description, String start, String end) {
        super(description.replaceFirst("event ", ""));
        this.start = start.replaceFirst("from", "");
        this.end = end.replaceFirst("to", "");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + start + "to:" + end + ")";
    }
}
