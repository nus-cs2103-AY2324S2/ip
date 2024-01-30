public class Event extends Task {
    private String start; // start date/time
    private String end; // end date/time

    public Event(String description, boolean completed, String start, String end) {
        super(description, completed);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

    public void setStart(String time) {
        this.start = time;
    }

    public void setEnd(String time) {
        this.end = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
