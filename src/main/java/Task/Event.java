package task;

public class Event extends Task {
    private TaskDateTime startTime;
    private TaskDateTime endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = new TaskDateTime(startTime);
        this.endTime = new TaskDateTime(endTime);
    }

    public Event(String description, String startTime, String endTime, boolean isDone) {
        super(description, isDone);
        this.startTime = new TaskDateTime(startTime);
        this.endTime = new TaskDateTime(endTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + startTime + " - " + endTime + ")";
    }

    @Override
    public String serialize() {
        return "E | " + super.serialize() + " | " + startTime.serialize() + " | " + endTime.serialize();
    }
}
