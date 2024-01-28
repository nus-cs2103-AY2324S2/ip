package task;

public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String description, String startTime, String endTime, boolean isDone) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + startTime + " - " + endTime + ")";
    }

    @Override
    public String serialize() {
        return "E | " + super.serialize() + " | " + startTime + " | " + endTime;
    }
}
