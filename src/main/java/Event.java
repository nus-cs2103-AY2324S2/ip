public class Event extends Task {
    protected String startString;
    protected String endString;

    public Event(String description, String start, String end) {
        super(description);
        this.startString = start;
        this.endString = end;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "]" + super.toString() + " (from:" + startString + "to:" + endString + ")" ; 
    }
}
