package bit;

public class Event extends Task {

    private final String start;
    private final String end;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        if(this.isDone) {
            return "[E][X] "+ DESCRIPTION + " (from: " + start + " to: " + end + ")";
        } else {
            return "[E][ ] " + DESCRIPTION + " (from: " + start + " to: " + end + ")";
        }
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }
}
