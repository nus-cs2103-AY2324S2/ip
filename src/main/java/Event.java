public class Event extends Task {
    private String start;
    private String end;
    public Event(String task, String start, String end) {
        super(task);
        this.start = start;
        this.end = end;
    }

    public Event(String task, String start, String end, boolean status) {
        super(task, status);
        this.start = start;
        this.end = end;
    }

    @Override
    public String stringify() {
        return "[E]" + super.stringify() + "(from: " + this.start + "to: " + this.end + ")";
    }

    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + start + " | " + end;
    }

}
