public class Event extends Task {
    private String start;
    private String end;

    Event(String desc, String start, String end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    Event(String desc, boolean isDone, String start, String end) {
        super(desc, isDone);
        this.start = start;
        this.end = end;
    }

    public String toSave() {
        // need to store status as well
        return "E - " + super.toSave() + " - " + start + " - " + end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
