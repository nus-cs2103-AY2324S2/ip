public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getTaskIcon(){
        return "E";
    }

    @Override
    public String toString() {
        return super.toString() + " (from " + start + " to " + end + ")";
    }
}
