public class Event extends Task {
    private String start;
    private String end;

    public Event(String item, String start, String end) {
        super(item);
        this.start = start;
        this.end = end;
    }

    @Override
    public String stringify() {
        String m = "";
        if (super.isMarked())
            m = "[X]";
        else
            m = "[ ]";
        return "[E]" + m + " " + super.stringify() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
