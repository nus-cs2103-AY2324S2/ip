public class Event extends Task {
    String start;
    String end;

    public Event(String description, String start, String end) {
        super(description);
        isEvent = true;
        this.start = start;
        this.end = end;
    }
}
