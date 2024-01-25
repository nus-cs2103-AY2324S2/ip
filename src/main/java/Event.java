public class Event extends Task {
    String start;
    String end;
    public Event(String task, String start, String end) {
        super(task);
        this.start = start;
        this.end = end;
    }

    @Override
    public String printTask() {
        return "[E]" + super.printTask() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
