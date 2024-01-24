public class Event extends Task {

    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getStatusIcon() {
        return "[E] " + super.getStatusIcon() + " " + description + " (from: " + start + " to: " + end + ")";
    }

}
