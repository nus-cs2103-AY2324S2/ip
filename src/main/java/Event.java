public class Event extends Task {
    private String start;
    private String end;

    Event(String desc, String start, String end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + start + "to:" + end + ")";
    }
}
