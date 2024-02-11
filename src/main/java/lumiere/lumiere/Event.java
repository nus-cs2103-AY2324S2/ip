package lumiere;

public class Event extends Task {
    private String start;
    private String end;

    public Event(String item, boolean marked, String start, String end) {
        super(item, marked);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
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
