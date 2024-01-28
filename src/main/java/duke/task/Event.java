package duke.task;

public class Event extends Task {

    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end; 
    }

    public Event(String description, String start, String end, Boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end; 
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }

    @Override
    public String toSave() {
        String strDone = this.isDone ? "1" : "0";
        return "E|" + strDone + "|" + this.description + "|" + this.start + "|" + this.end;
    }
}