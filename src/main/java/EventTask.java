public class EventTask extends Task {
    private String type;
    private String start;
    private String end;
    public EventTask(String what, String start, String end) {
        super(what);
        this.type = "[E]";
        this.start = start;
        this.end = end;
    }
    public String showAll() {
        return this.type + super.showAll()
                + "(from: " + this.start + " to: " + this.end + ")";
    }
}
