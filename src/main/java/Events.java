public class Events extends Task {

    protected String start;
    protected String end;

    public Events(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to:" + end + ")";
    }
}
