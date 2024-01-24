public class Event extends Task {
    String startTime;
    String endTime;

    public Event(String str, String startTime, String endTime) {
        super(str);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + this.desc + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
