public class Event extends Task {
    String start;
    String end;

    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        isEvent = true;
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
