public class Event extends Task {
    String start;
    String end;

    public Event(String description, String start, String end, boolean isDone) {
        super(description);
        isEvent = true;
        this.start = start;
        this.end = end;
        this.isDone = isDone;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
