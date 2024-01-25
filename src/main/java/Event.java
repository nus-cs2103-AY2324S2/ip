public class Event extends Task {
    private String start;
    private String end;

    public Event(String start, String end, String task) {
        super(task);
        this.start = start;
        this.end = end;
    }

    public String isEvent() {
        return "[E]";
    }

    public String getEvent() {
        return "(from: " + start + " to: " + end + ")";
    }

    public String addEvent() {
        return "Got it. I've added this task:\n"
                + "    " + this.isEvent() + this.marked() + " "
                + this.getTask() + this.getEvent();
    }
}
