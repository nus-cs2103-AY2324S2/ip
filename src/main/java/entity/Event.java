package entity;

public class Event extends Task {
    private String startTime;
    private String endTime;
    public Event(String title, String startTime, String endTime) {
        super(title);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        if (this.marked) {
            return "[E][X] " + this.title + " (from: " + this.startTime + " to: " + this.endTime + ")";
        } else {
            return "[E][ ] " + this.title + " (from: " + this.startTime + " to: " + this.endTime + ")";
        }
    }

}
