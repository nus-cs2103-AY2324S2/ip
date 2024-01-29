public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }    

    public Event(String name, boolean isDone, String start, String end) {
        super(name, isDone);
        this.start = start;
        this.end = end;
    }    

    @Override
    public String toSave() {
        return "E" + super.toSave() + " | " + start + " | " + end;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end +")";
    }
}
