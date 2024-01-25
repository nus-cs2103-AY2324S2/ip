public class Event extends Task {
    String from;
    String to;
    public Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return "[ ]" + super.toString() + " (from: " + from + " to " + to + ")";
    }

}
