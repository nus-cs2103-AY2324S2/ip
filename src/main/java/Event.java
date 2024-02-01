public class Event extends Task {
    String start;
    String end;
    public Event(String task, String start, String end) {
        super(task);
        this.start = start;
        this.end = end;
    }

    public Event(String task, String start, String end, boolean done) {
        super(task, done);
        this.start = start;
        this.end = end;
    }

    @Override
    public String printTask() {
        return "[E]" + super.printTask() + " (from: " + this.start + " to: " + this.end + ")";
    }

    @Override
    public String toString() {
        int marked = this.done ? 1 : 0;
        return "E | " + marked + " | " + this.task + " | " + this.start + " | " + this.end;
    }
}
