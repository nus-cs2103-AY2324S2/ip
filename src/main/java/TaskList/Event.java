package TaskList;

public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String description, String startTime, String endTime, boolean isDone) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + this.getStatusIcon() + " " + this.getDescription() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

    @Override
    public String toStorageString() {
        return "E | " + this.getDescription() + " | " + super.getStatus() + " | " + this.startTime + " | " + this.endTime;
    }


}
