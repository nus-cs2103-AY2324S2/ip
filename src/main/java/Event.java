public class Event extends Task {
    private String start;
    private String end;


    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String toString() {
        return "[E]" + "[" + (isDone ? "X" : " ") + "] " + description
                + "(from: " + start
                + "to: "+ end + ")";
    }
}
