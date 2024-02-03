package duke.task;

public class Event extends Task {
    private String start;
    private String end;

    public Event(String taskName, boolean isDone, String start, String end) {
        super(taskName, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String storeData() {

        return "event|" +super.storeData() + "|" + this.start + "|" + this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                String.format(" (from: %s to: %s)", start, end);
    }
}
