public class Event extends Task {
    private String start;
    private String end;
    public Event(String task, String start, String end) {
        super(task);
        this.start = start;
        this.end = end;
    }

    @Override
    public String stringify() {
        return "[E]" + super.stringify() + "(from: " + this.start + "to: " + this.end + ")";
    }

}
