public class Event extends Task{
    private String start;
    private String end;

    public Event(String task, String start, String end) {
        super(task);
        this.start = start;
        this.end = end;
    }

    @Override
    public String printTask() {
        return "[E]" + super.printTask() + " (from: " + this.start + " to: " + this.end + ")";
    }

    @Override
    public String toString() {
        return String.format("E | %s | %s | %s", super.toString(), this.start, this.end);
    }
}
