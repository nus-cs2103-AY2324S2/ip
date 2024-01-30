public class Event extends Task {
    String from;
    String to;

    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    public String getStart() {
        return this.from;
    }

    public String getEnd() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "( from: " + from + " to: " + to + ")";
    }

}
